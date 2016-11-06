package com.jyyx.service;

import java.util.List;
import java.util.Map;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.mysql.entity.MsgCategory;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.utils.PageData;

/**
 * andy xu
 * 2016年11月6日
 */
public interface MsgCategoryService {
	
	/** 添加资源 */
	void addResources(MsgCategory msgCategory);
	
	/** 修改资源 
	 * @throws JyException */
	void modifyResources(MsgCategory msgCategory) throws JyException;
	
	/** 批量修改排序号 
	 * @throws JyException */
	void modifyResourcesOrders(Map<Integer, Integer> msgCategoryOrders) throws JyException;
	
	/** 查询资源 */
	List<MsgCategory> getResources(MsgCategory msgCategory);
	
	/** 删除资源 */
	void deleteResources(int resourceId);
	
	/** 根据ID查询资源 */
	MsgCategory getResourcesById(int resourceId);
}


