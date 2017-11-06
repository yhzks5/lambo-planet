package com.lambo.upms.server.controller;

import com.lambo.common.base.BaseController;
import com.lambo.common.util.PropertiesFileUtil;
import com.lambo.common.util.RedisUtil;
import com.lambo.upms.client.shiro.session.UpmsSession;
import com.lambo.upms.client.shiro.session.UpmsSessionDao;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import com.lambo.upms.dao.model.UpmsSystem;
import com.lambo.upms.dao.model.UpmsSystemExample;
import com.lambo.upms.rpc.api.UpmsSystemService;
import com.lambo.upms.rpc.api.UpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 单点登录管理
 * Created by lambo on 2016/12/10.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class SSOController extends BaseController {

    private final static Logger _log = LoggerFactory.getLogger(SSOController.class);
    // 全局会话key
    private final static String LAMBO_UPMS_SERVER_SESSION_ID = "lambo-upms-server-session-id";
    // 全局会话key列表
    private final static String LAMBO_UPMS_SERVER_SESSION_IDS = "lambo-upms-server-session-ids";
    // code key
    private final static String LAMBO_UPMS_SERVER_CODE = "lambo-upms-server-code";

    @Autowired
    UpmsSystemService upmsSystemService;

    @Autowired
    UpmsUserService upmsUserService;

    @Autowired
    UpmsSessionDao upmsSessionDao;


    @ApiOperation(value = "登录" ,notes = "验证登录信息,返回结果,如果当前已有用户登录,在执行注销操作前,会一直返回成功结果")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(
            @ApiParam(name = "username", required = true, value = "用户名")
            @RequestParam(value = "username") String username,
            @ApiParam(name = "password", required = true, value = "密码")
            @RequestParam(value = "password") String password,
            @ApiParam(name = "rememberMe", required = false, value = "记住密码(true/false)")
            @RequestParam(value = "rememberMe", required=false) String rememberMe,
            @ApiParam(name = "backurl", required = false, value = "登录成功后跳转的url")
            @RequestParam(value = "backurl", required=false) String backurl) {


        if (StringUtils.isBlank(username)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisUtil.get(LAMBO_UPMS_SERVER_SESSION_ID + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe)) {
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    usernamePasswordToken.setRememberMe(false);
                }
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "帐号不存在！");
            } catch (IncorrectCredentialsException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码错误！");
            } catch (LockedAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
            }
            // 更新session状态
            upmsSessionDao.updateStatus(sessionId, UpmsSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            RedisUtil.lpush(LAMBO_UPMS_SERVER_SESSION_IDS, sessionId.toString());
            // 默认验证帐号密码正确，创建code
            String code = UUID.randomUUID().toString();
            // 全局会话的code
            RedisUtil.set(LAMBO_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
            // code校验值
            RedisUtil.set(LAMBO_UPMS_SERVER_CODE + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);
        }
        // 回跳登录前地址
        if (StringUtils.isBlank(backurl)) {
            UpmsSystem upmsSystem = upmsSystemService.selectUpmsSystemByName(PropertiesFileUtil.getInstance().get("app.name"));
            backurl = null == upmsSystem ? "/" : upmsSystem.getBasepath();
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
        }
    }

    @ApiOperation(value = "校验code",notes = "用于单点登录时校验code是否合法")
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public Object code(
            @ApiParam(name = "codeParam", required = true, value = "待校验的code")
            @RequestParam(value = "codeParam") String codeParam) {

        String code = RedisUtil.get(LAMBO_UPMS_SERVER_CODE + "_" + codeParam);
        if (StringUtils.isBlank(codeParam) || !codeParam.equals(code)) {
            new UpmsResult(UpmsResultConstant.FAILED, "无效code");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, code);
    }

    @ApiOperation(value = "退出登录", notes = "退出当前用户, 啥参数也不用传")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout() {
        // shiro退出登录
        SecurityUtils.getSubject().logout();

        return new UpmsResult(UpmsResultConstant.SUCCESS, "注销成功");
    }

}