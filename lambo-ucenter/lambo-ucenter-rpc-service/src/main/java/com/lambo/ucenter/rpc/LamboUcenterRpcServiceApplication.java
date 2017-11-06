package com.lambo.ucenter.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by lambo on 2017/6/19.
 */
public class LamboUcenterRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(LamboUcenterRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> lambo-ucenter-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		_log.info(">>>>> lambo-ucenter-rpc-service 启动完成 <<<<<");
	}

}
