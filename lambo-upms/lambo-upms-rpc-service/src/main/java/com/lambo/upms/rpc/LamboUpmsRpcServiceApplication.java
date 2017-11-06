package com.lambo.upms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by lambo on 2017/2/3.
 */
public class LamboUpmsRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(LamboUpmsRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> lambo-upms-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		_log.info(">>>>> lambo-upms-rpc-service 启动完成 <<<<<");
	}

}
