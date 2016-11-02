package com.jyyx.dao.utils;

import com.jyyx.dao.mysql.entity.User;

/**
 * 请求中user对象保存类
 * andy xu
 * 2016年11月2日
 */
public class UserUtils {

	private static ThreadLocal<User> userHolder = new ThreadLocal<User>();
	
	public static void setUser(User user) {
		userHolder.set(user);
	}
	
	public static User getUser() {
		return userHolder.get();
	}
}
