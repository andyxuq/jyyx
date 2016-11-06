package com.jyyx.dao;

import java.util.List;

import com.jyyx.dao.mysql.entity.MsgCategory;

/**
 * 资讯分类dao接口
 * andy xu
 * 2016年11月6日
 */
public interface MsgCategoryDao {

	/**
	 * 根据ID查找资源
	 * @param picId
	 * @return
	 */
	MsgCategory getResourcesById(int resourceId);
	
	/** 查询资源 */
	List<MsgCategory> getResources(MsgCategory msgCategory);
	
	/** 删除资源  */
	void deleteResources(int resourceId);
	
	/** 添加资源 */
	void addResources(MsgCategory msgCategory);
	
	/** 修改资源 */
	void modifyResources(MsgCategory msgCategory);
}
