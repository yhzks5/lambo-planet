package com.lambo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压lamboAdmin-x.x.x.jar到resources目录
 * Created by lambo on 2016/12/18.
 */
public class LamboAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger _log = LoggerFactory.getLogger(LamboAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        _log.info("===== 开始解压lambo-admin =====");
        String version = PropertiesFileUtil.getInstance("lambo-admin-client").get("lambo.admin.version");
        _log.info("lambo-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/lambo-admin-" + version + ".jar");
        _log.info("lambo-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/lambo-admin";
        _log.info("lambo-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压lambo-admin完成 =====");
    }

}
