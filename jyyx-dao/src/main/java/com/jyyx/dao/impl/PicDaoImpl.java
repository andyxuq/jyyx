package com.jyyx.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jyyx.dao.PicDao;
import com.jyyx.dao.mysql.dao.PicMapper;
import com.jyyx.dao.mysql.dao.extend.PicExtendMapper;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.mysql.entity.PicExample.Criteria;
import com.jyyx.dao.mysql.entity.extend.PicExtendExample;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

@Repository
public class PicDaoImpl implements PicDao {

	@Autowired 
	private PicMapper picMapper;
	
	@Autowired 
	private PicExtendMapper picExtendMapper;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.PicDao#getResourcesCount(com.jyyx.dao.mysql.entity.Pic)
	 */
	public int getResourcesCount(Pic pic) {
		PicExtendExample example = getSearchExample(pic);
		return picMapper.countByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.PicDao#getResourcesById(int)
	 */
	public Pic getResourcesById(int picId) {
		return picMapper.selectByPrimaryKey(picId);
	}



	/* (non-Javadoc)
	 * @see com.jyyx.dao.PicDao#getResources(com.jyyx.dao.mysql.entity.Pic)
	 */
	public List<Pic> getResources(Pic pic) {
		PicExtendExample example = getSearchExample(pic);		
		return picMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.PicDao#getResourcesWithPage(com.jyyx.dao.mysql.entity.Pic, com.jyyx.dao.utils.PageInfo)
	 */
	public PageData<Pic> getResourcesWithPage(Pic pic, PageInfo pageInfo) {
		PicExtendExample example = getSearchExample(pic);
		example.setStartRow(pageInfo.getStartRow());
		example.setPageRow(pageInfo.getPageRow());
		List<Pic> picList = picExtendMapper.selectByExample(example);
		
		PageData<Pic> pageData = new PageData<Pic>(pageInfo);
		pageData.setPageData(picList);
		return pageData;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.PicDao#deleteResources(int)
	 */
	public void deleteResources(int picId) {
		picMapper.deleteByPrimaryKey(picId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.PicDao#addResources(com.jyyx.dao.mysql.entity.Pic)
	 */
	public void addResources(Pic pic) {
		pic.setCreateTime(new Date());
		picMapper.insertSelective(pic);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.PicDao#modifyResources(com.jyyx.dao.mysql.entity.Pic)
	 */
	public void modifyResources(Pic pic) {
		picMapper.updateByPrimaryKeySelective(pic);
	}

	private PicExtendExample getSearchExample(Pic pic) {
		PicExtendExample example = new PicExtendExample();
		Criteria criteria = example.createCriteria();
		if (pic.getPicCode() != null) {
			criteria.andPicCodeEqualTo(pic.getPicCode());
		}
		if (pic.getReferId() != null) {
			criteria.andReferIdEqualTo(pic.getReferId());
		}
		return example;
	}
}
