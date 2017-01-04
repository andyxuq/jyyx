package com.jyyx.dao;

import java.util.List;

import org.springframework.beans.BeansException;

import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.mysql.entity.User;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * 
 * andy xu
 * 2016年11月9日
 */
public interface UserDao {

	/**
	 * 根据ID查找资源
	 * @param resourceId
	 * @return
	 */
	List<User> getResources(User param);
	
	/**
	 * 根据ID查找资源
	 * @param resourceId
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws BeansException 
	 */
	User getResourcesById(int resourceId);
	
	/** 删除资源  */
	void deleteResources(int resourceId);
	
	/** 添加资源 */
	void addResources(User param);
	
	/** 修改资源 */
	void modifyResources(User param);
}
