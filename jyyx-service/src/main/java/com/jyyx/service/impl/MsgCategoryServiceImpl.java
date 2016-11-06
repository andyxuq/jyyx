package com.jyyx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.MsgCategoryDao;
import com.jyyx.dao.mysql.entity.MsgCategory;
import com.jyyx.service.MsgCategoryService;

/**
 * andy xu
 * 2016年11月6日
 */
@Service
public class MsgCategoryServiceImpl implements MsgCategoryService {
	
	@Autowired
	private MsgCategoryDao msgCategoryDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgCategoryService#addResources(com.jyyx.dao.mysql.entity.MsgCategory)
	 */
	public void addResources(MsgCategory msgCategory) {
		msgCategoryDao.addResources(msgCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgCategoryService#modifyResources(com.jyyx.dao.mysql.entity.MsgCategory)
	 */
	public void modifyResources(MsgCategory msgCategory) throws JyException {
		if (msgCategory.getId() == null) {
			throw new JyException("修改资源时，资源ID为空");
		}
		msgCategoryDao.modifyResources(msgCategory);		
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgCategoryService#modifyResourcesOrders(java.util.Map)
	 */
	@Transactional
	public void modifyResourcesOrders(Map<Integer, Integer> msgCategoryOrders) throws JyException {
		for (Map.Entry<Integer, Integer> entry : msgCategoryOrders.entrySet()) {
			Integer resourceId = entry.getKey();
			Integer orderCode = entry.getValue();
			
			MsgCategory msgCategory = msgCategoryDao.getResourcesById(resourceId);
			if (null == msgCategory) {
				throw new JyException("不能修改资源" + resourceId + "的排序号，资源部存在");
			}
			msgCategory.setOrderCode(orderCode);
			msgCategoryDao.modifyResources(msgCategory);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgCategoryService#getResources(com.jyyx.dao.mysql.entity.MsgCategory)
	 */
	public List<MsgCategory> getResources(MsgCategory msgCategory) {
		return msgCategoryDao.getResources(msgCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgCategoryService#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		msgCategoryDao.deleteResources(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgCategoryService#getResourcesById(int)
	 */
	public MsgCategory getResourcesById(int resourceId) {
		return msgCategoryDao.getResourcesById(resourceId);
	}
}
