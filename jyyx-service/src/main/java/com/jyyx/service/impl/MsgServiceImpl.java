package com.jyyx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.core.exception.JyException;
import com.jyyx.dao.MsgDao;
import com.jyyx.dao.mysql.entity.Msg;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;
import com.jyyx.service.MsgService;

/**
 * andy xu
 * 2016年11月7日
 */
@Service
public class MsgServiceImpl implements MsgService {

	/**
	 * 数据库操作dao对象
	 */
	@Autowired private MsgDao msgDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgService#addResource(com.jyyx.dao.mysql.entity.Msg)
	 */
	public void addResource(Msg msg) {
		msgDao.addResources(msg);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgService#modifyResources(com.jyyx.dao.mysql.entity.Msg)
	 */
	public void modifyResources(Msg msg) {
		msgDao.modifyResources(msg);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgService#modifyOrders(java.util.Map)
	 */
	@Transactional(rollbackFor = {Exception.class})
	public void modifyOrders(Map<Integer, Integer> msgOrders) throws JyException {
		for (Map.Entry<Integer, Integer> entry : msgOrders.entrySet()) {
			Msg msg = msgDao.getResourcesById(entry.getKey());
			if (null == msg) {
				throw new JyException("无法修改" + entry.getKey() + "的排序号，资讯不存在");
			}
			
			msg.setOrderCode(entry.getValue());
			msgDao.modifyResources(msg);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgService#getResources(com.jyyx.dao.mysql.entity.Msg)
	 */
	public List<Msg> getResources(Msg msg) {
		return msgDao.getResources(msg);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgService#getPicResourcesWithPage(com.jyyx.dao.mysql.entity.Msg, int, int)
	 */
	public PageData<Msg> getPicResourcesWithPage(Msg msg, int page, int pageRow) {
		int totalCount = msgDao.getResourcesCount(msg);
		PageInfo pageInfo = new PageInfo(page, pageRow, totalCount);
		
		return msgDao.getResourcesWithPage(msg, pageInfo);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgService#deleteMsgResources(int)
	 */
	public void deleteMsgResources(int msgId) {
		msgDao.deleteResources(msgId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.MsgService#getResourceById(int)
	 */
	public Msg getResourceById(int msgId) {
		return msgDao.getResourcesById(msgId);
	}

}
