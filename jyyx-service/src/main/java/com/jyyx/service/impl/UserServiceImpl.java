package com.jyyx.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyyx.dao.UserDao;
import com.jyyx.dao.mysql.entity.User;
import com.jyyx.service.UserService;
import com.jyyx.service.utils.Md5Utils;

/**
 * andy xu
 * 2017年1月3日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.UserService#getResources()
	 */
	public List<User> getResources(User param) {
		List<User> userList = userDao.getResources(param);
		return userList;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.UserService#addResource(com.jyyx.dao.mysql.entity.User)
	 */
	public void addResource(User user) {
		String loginPwd = user.getLoginPwd();
		String pwd = Md5Utils.generate(loginPwd);
		
		user.setLoginPwd(pwd);
		user.setCreateTime(new Date());
		userDao.addResources(user);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.UserService#modifyResources(com.jyyx.dao.mysql.entity.User)
	 */
	public void modifyResources(User user) {
		if (StringUtils.isNotBlank(user.getLoginPwd())) {
			String pwd = Md5Utils.generate(user.getLoginPwd());
			user.setLoginPwd(pwd);
		} else {
			user.setLoginPwd(null);
		}
		userDao.modifyResources(user);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.UserService#getResourceById(int)
	 */
	public User getResourceById(int resourceId) {
		User user = userDao.getResourcesById(resourceId);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.UserService#deleteResource(int)
	 */
	public void deleteResource(int resourceId) {
		userDao.deleteResources(resourceId);
	}

}
