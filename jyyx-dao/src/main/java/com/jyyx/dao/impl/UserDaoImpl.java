package com.jyyx.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.UserDao;
import com.jyyx.dao.mysql.dao.UserMapper;
import com.jyyx.dao.mysql.entity.User;
import com.jyyx.dao.mysql.entity.UserExample;

/**
 * 用户表dao接口实现
 * andy xu
 * 2017年1月3日
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired 
	private UserMapper userMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.UserDao#getResources(int)
	 */
	public List<User> getResources(User param) {
		UserExample example = new UserExample();
		if (StringUtils.isNotBlank(param.getLoginName())) {
			example.createCriteria().andLoginNameEqualTo(param.getLoginName());
		}
		return userMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.UserDao#getResourcesById(int)
	 */
	public User getResourcesById(int resourceId) {
		return userMapper.selectByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.UserDao#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		userMapper.deleteByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.UserDao#addResources(com.jyyx.dao.mysql.entity.User)
	 */
	public void addResources(User param) {
		userMapper.insertSelective(param);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.UserDao#modifyResources(com.jyyx.dao.mysql.entity.User)
	 */
	public void modifyResources(User param) {
		userMapper.updateByPrimaryKeySelective(param);
	} 
}
