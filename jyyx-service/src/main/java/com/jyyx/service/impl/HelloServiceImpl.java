package com.jyyx.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.dao.mysql.dao.PicMapper;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.service.HelloService;

/**
 * andy xu
 * 2016年10月29日
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Autowired private PicMapper picMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.HelloService#sayHello()
	 */
	@Transactional
	public void sayHello() {
		Pic pic = new Pic();
		pic.setCreateTime(new Date());
		pic.setPicPath("/good/hello");
		pic.setPicCode("nimeia");
		
		picMapper.insert(pic);
		System.out.println("hello");
	}
}
