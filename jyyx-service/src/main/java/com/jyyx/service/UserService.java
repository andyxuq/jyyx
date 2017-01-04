package com.jyyx.service;

import java.util.List;

import org.springframework.beans.BeansException;

import com.jyyx.dao.mysql.entity.User;

public interface UserService {

	/** 查询资源
	 * @return
	 */
	List<User> getResources(User param);
	
	/** 添加资源 */
	void addResource(User user);
	
	/** 修改资源 */
	void modifyResources(User user);
	
	/** 根据ID查找资源 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException */
	User getResourceById(int resourceId);
	
	/**
	 * 删除资源
	 * @param resourceId
	 */
	void deleteResource(int resourceId);
}
