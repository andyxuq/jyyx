package com.jyyx.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyyx.service.HelloService;

/**
 * andy xu
 * 2016年10月29日
 */
@Controller
public class HelloController {

	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public Object hello() {
		try {
			helloService.sayHello();
			return "good";
		} catch (Exception e) {
			logger.error("bad info", e);
			return "bad";
		}
	}
}
