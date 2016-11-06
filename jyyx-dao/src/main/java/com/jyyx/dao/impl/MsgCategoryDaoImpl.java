package com.jyyx.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.MsgCategoryDao;
import com.jyyx.dao.mysql.dao.MsgCategoryMapper;
import com.jyyx.dao.mysql.entity.MsgCategory;
import com.jyyx.dao.mysql.entity.MsgCategoryExample;
import com.jyyx.dao.mysql.entity.MsgCategoryExample.Criteria;

/**
 * 
 * andy xu
 * 2016年11月6日
 */
@Repository
public class MsgCategoryDaoImpl implements MsgCategoryDao {

	@Autowired
	private MsgCategoryMapper msgCategoryMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgCategoryDao#getResourcesById(int)
	 */
	public MsgCategory getResourcesById(int resourceId) {
		return msgCategoryMapper.selectByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgCategoryDao#getResources(com.jyyx.dao.mysql.entity.MsgCategory)
	 */
	public List<MsgCategory> getResources(MsgCategory msgCategory) {
		MsgCategoryExample example = new MsgCategoryExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(msgCategory.getCategoryName())) {
			criteria.andCategoryNameLike("%" + msgCategory.getCategoryName() + "%");
		}
		if (msgCategory.getId() != null) {
			criteria.andIdEqualTo(msgCategory.getId());
		}
		example.setOrderByClause("order_code asc, create_time desc");
		return msgCategoryMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgCategoryDao#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		msgCategoryMapper.deleteByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgCategoryDao#addResources(com.jyyx.dao.mysql.entity.MsgCategory)
	 */
	public void addResources(MsgCategory msgCategory) {
		msgCategory.setCreateTime(new Date());
		msgCategoryMapper.insertSelective(msgCategory);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.MsgCategoryDao#modifyResources(com.jyyx.dao.mysql.entity.MsgCategory)
	 */
	public void modifyResources(MsgCategory msgCategory) {
		msgCategoryMapper.updateByPrimaryKeySelective(msgCategory);
	}
}
