package com.jyyx.dao;

import java.util.List;

import com.jyyx.dao.mysql.entity.Msg;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * 资讯操作数据库dao接口
 * andy xu
 * 2016年11月7日
 */
public interface MsgDao {


	/** 查询资源总数 */
	int getResourcesCount(Msg msg);
	
	/**
	 * 根据ID查找资源
	 * @param picId
	 * @return
	 */
	Msg getResourcesById(int msgId);
	
	/** 查询资源 */
	List<Msg> getResources(Msg msg);
	
	/** 分页查询资源 */
	PageData<Msg> getResourcesWithPage(Msg msg, PageInfo pageInfo);
	
	/** 删除资源  */
	void deleteResources(int msgId);
	
	/** 添加资源 */
	void addResources(Msg msg);
	
	/** 修改资源 */
	void modifyResources(Msg Msg);
}
