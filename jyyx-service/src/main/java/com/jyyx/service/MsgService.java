package com.jyyx.service;

import java.util.List;
import java.util.Map;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.mysql.entity.Msg;
import com.jyyx.dao.utils.PageData;

/**
 * andy xu
 * 2016年11月7日
 */
public interface MsgService {

	/** 批量添加资讯资源 */
	void addResource(Msg msg);
	
	/** 修改资讯资源 */
	void modifyResources(Msg msg);
	
	/** 批量修改资讯排序号 
	 * @throws JyException */
	void modifyOrders(Map<Integer, Integer> msgOrders) throws JyException;
	
	/** 查询资讯资源 */
	List<Msg> getResources(Msg msg);
	
	/** 分页查询资讯资源 */
	PageData<Msg> getPicResourcesWithPage(Msg msg, int page, int pageRow);
	
	/** 删除资讯资源 */
	void deleteMsgResources(int msgId);
	
	/** 根据ID查找资源 */
	Msg getResourceById(int msgId);

}
