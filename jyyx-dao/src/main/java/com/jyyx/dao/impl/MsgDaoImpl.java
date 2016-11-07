package com.jyyx.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.MsgDao;
import com.jyyx.dao.mysql.dao.MsgMapper;
import com.jyyx.dao.mysql.dao.extend.MsgExtendMapper;
import com.jyyx.dao.mysql.entity.Msg;
import com.jyyx.dao.mysql.entity.MsgExample.Criteria;
import com.jyyx.dao.mysql.entity.extend.MsgExtendExample;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * msg数据库dao实现
 * andy xu
 * 2016年11月7日
 */
@Repository
public class MsgDaoImpl implements MsgDao {

	@Autowired
	private MsgExtendMapper msgExtendMapper;
	
	@Autowired
	private MsgMapper msgMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgDao#getResourcesCount(com.jyyx.dao.mysql.entity.Msg)
	 */
	public int getResourcesCount(Msg msg) {
		MsgExtendExample example = getSearchExample(msg);
		return msgMapper.countByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgDao#getResourcesById(int)
	 */
	public Msg getResourcesById(int msgId) {
		return msgMapper.selectByPrimaryKey(msgId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgDao#getResources(com.jyyx.dao.mysql.entity.Msg)
	 */
	public List<Msg> getResources(Msg msg) {
		MsgExtendExample example = getSearchExample(msg);
		example.setOrderByClause("order_code asc, create_time desc");
		return msgMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgDao#getResourcesWithPage(com.jyyx.dao.mysql.entity.Msg, com.jyyx.dao.utils.PageInfo)
	 */
	public PageData<Msg> getResourcesWithPage(Msg msg, PageInfo pageInfo) {
		MsgExtendExample example = getSearchExample(msg);
		example.setStartRow(pageInfo.getStartRow());
		example.setPageRow(pageInfo.getPageRow());
		example.setOrderByClause("order_code asc, create_time desc");
		List<Msg> msgList = msgExtendMapper.selectByExample(example);
		
		PageData<Msg> pageData = new PageData<Msg>(pageInfo);
		pageData.setPageData(msgList);
		return pageData;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgDao#deleteResources(int)
	 */
	public void deleteResources(int msgId) {
		msgMapper.deleteByPrimaryKey(msgId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgDao#addResources(com.jyyx.dao.mysql.entity.Msg)
	 */
	public void addResources(Msg msg) {
		msg.setCreateTime(new Date());
		msgMapper.insertSelective(msg);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgDao#modifyResources(com.jyyx.dao.mysql.entity.Msg)
	 */
	public void modifyResources(Msg msg) {
		msgMapper.updateByPrimaryKeySelective(msg);
	}
	
	private MsgExtendExample getSearchExample(Msg msg) {
		MsgExtendExample example = new MsgExtendExample();
		Criteria criteria = example.createCriteria();
		if (msg.getId() != null) {
			criteria.andIdEqualTo(msg.getId());
		}
		if (StringUtils.isNotBlank(msg.getMsgTitle())) {
			criteria.andMsgTitleLike("%" + msg.getMsgTitle() + "%");
		}
		if (msg.getCategoryId() != null) {
			criteria.andCategoryIdEqualTo(msg.getCategoryId());
		}
		return example;
	}
}
