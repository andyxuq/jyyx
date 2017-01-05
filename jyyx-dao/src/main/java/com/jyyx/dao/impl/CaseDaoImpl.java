package com.jyyx.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.jyyx.core.enums.PicCodeType;
import com.jyyx.dao.CaseCateRelationDao;
import com.jyyx.dao.CaseCategoryDao;
import com.jyyx.dao.CaseDao;
import com.jyyx.dao.PicDao;
import com.jyyx.dao.model.CaseParam;
import com.jyyx.dao.model.JyCase;
import com.jyyx.dao.model.JyCaseCategory;
import com.jyyx.dao.mysql.dao.CaseMapper;
import com.jyyx.dao.mysql.dao.extend.CaseExtendMapper;
import com.jyyx.dao.mysql.entity.Case;
import com.jyyx.dao.mysql.entity.CaseCateRelation;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.utils.ModelUtils;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;

/**
 * 产品dao实现
 * andy xu
 * 2016年11月10日
 */
@Repository
public class CaseDaoImpl implements CaseDao {

	@Autowired
	private CaseMapper caseMapper;
	
	@Autowired 
	CaseExtendMapper caseExtendMapper;
	
	@Autowired
	private CaseCateRelationDao ccrDao;
	
	@Autowired 
	private CaseCategoryDao caseCategoryDao;
	
	@Autowired 
	private PicDao picDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResourcesCount(com.jyyx.dao.model.ProductParam)
	 */
	public int getResourcesCount(CaseParam param) {
		return caseExtendMapper.countByParam(param);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResourcesById(int)
	 */
	public JyCase getResourcesById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException {
		CaseParam param = new CaseParam();
		param.setId(resourceId);
		List<JyCase> caseList = caseExtendMapper.selectByParam(param);
		
		fetchPicRes(caseList);
		fetchCategoryRelation(caseList);
		return caseList.isEmpty() ? null : caseList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResources(com.jyyx.dao.model.ProductParam)
	 */
	public List<JyCase> getResources(CaseParam param) throws BeansException, InstantiationException, IllegalAccessException {
		List<JyCase> jyCaseList = caseExtendMapper.selectByParam(param);
		fetchPicRes(jyCaseList);
		fetchCategoryRelation(jyCaseList);
		return jyCaseList;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#getResourcesWithPage(com.jyyx.dao.model.ProductParam, com.jyyx.dao.utils.PageInfo)
	 */
	public PageData<JyCase> getResourcesWithPage(CaseParam param, PageInfo pageInfo) throws BeansException, InstantiationException, IllegalAccessException {
		param.setStartRow(pageInfo.getStartRow());
		param.setPageRow(pageInfo.getPageRow());
		
		List<JyCase> jyCaseList = caseExtendMapper.selectByParam(param);
		int toIndex = pageInfo.getStartRow() + pageInfo.getPageRow();
		if (toIndex > pageInfo.getTotalCount()) {
			toIndex = pageInfo.getTotalCount();
		}
		List<JyCase> resultList = jyCaseList.subList(pageInfo.getStartRow(), toIndex);
		
		fetchPicRes(resultList);
		fetchCategoryRelation(resultList);
		
		PageData<JyCase> pageData = new PageData<JyCase>(pageInfo);
		pageData.setPageData(resultList);
		return pageData;
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		ccrDao.deleteResourcesByResId(resourceId);
		caseMapper.deleteByPrimaryKey(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#addResources(com.jyyx.dao.model.ProductParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addResources(CaseParam param) throws BeansException, InstantiationException, IllegalAccessException {
		Case caseData = getCaseFromParam(param);
		caseExtendMapper.insertSelective(caseData);
		for (CaseCateRelation ccr : param.getCaseCategorys()) {
			ccr.setCaseId(caseData.getId());
			ccrDao.addResources(ccr);
		}
	}

	/* (non-Javadoc)
	 * @see com.jyyx.dao.ProductDao#modifyResources(com.jyyx.dao.model.ProductParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResources(CaseParam param) throws BeansException, InstantiationException, IllegalAccessException {
		ccrDao.deleteResourcesByResId(param.getId());
		
		Case caseData = getCaseFromParam(param);
		caseData.setId(param.getId());
		
		caseMapper.updateByPrimaryKeySelective(caseData);
		for (CaseCateRelation ccr : param.getCaseCategorys()) {
			ccr.setCaseId(caseData.getId());
			ccrDao.addResources(ccr);
		}
	}
	
	private Case getCaseFromParam(CaseParam param) throws BeansException, InstantiationException, IllegalAccessException {
		Case caseData = ModelUtils.copyProperty(param, Case.class);
		caseData.setCreateTime(new Date());
		
		return caseData;
	}
	
	private void fetchPicRes(List<JyCase> caseList) {
		Map<Integer, JyCase> caseMap = new HashMap<Integer, JyCase>();
		for (JyCase jyCase : caseList) {
			caseMap.put(jyCase.getId(), jyCase);
		}
		
		if (caseMap.size() > 0) {
			Map<Integer, List<Pic>> picMap = picDao.getResourceByCode(PicCodeType.DECORATE_HEADER, caseMap.keySet());
			for (int id : picMap.keySet()) {
				caseMap.get(id).setPics(picMap.get(id));
			}
		}
	}
	
	private void fetchCategoryRelation(List<JyCase> jyCaseList) throws BeansException, InstantiationException, IllegalAccessException {
		Map<Integer, JyCaseCategory> parentMap = new HashMap<Integer, JyCaseCategory>();
		Set<Integer> parentIds = new HashSet<Integer>();
		
		for (JyCase jyCase : jyCaseList) {
			for (JyCaseCategory category : jyCase.getCaseCategorys()) {
				if (category.getParentId() != 0) {
					parentIds.add(category.getParentId());
				}
			}
		}
		if (!parentIds.isEmpty()) {
			List<JyCaseCategory> parentCategoryList = caseCategoryDao.getResouceByIds(parentIds);
			for (JyCaseCategory parentCategory : parentCategoryList) {
				parentMap.put(parentCategory.getId(), parentCategory);
			}
		}

		for (JyCase jyCase : jyCaseList) {
			List<JyCaseCategory> categoryList = new ArrayList<JyCaseCategory>();
			for (JyCaseCategory childCategory : jyCase.getCaseCategorys()) {
				JyCaseCategory parentCategory = parentMap.get(childCategory.getParentId());
				if (null != parentCategory) {
					JyCaseCategory newParent = new JyCaseCategory();
					newParent.setId(parentCategory.getId());
					newParent.setCategoryName(parentCategory.getCategoryName());
					newParent.setCreateTime(parentCategory.getCreateTime());
					newParent.setUpdatedTime(parentCategory.getUpdatedTime());
					newParent.setOrderCode(parentCategory.getOrderCode());
					newParent.setParentId(parentCategory.getParentId());
					
					newParent.getChildList().add(childCategory);
					categoryList.add(newParent);
				} else {
					categoryList.add(childCategory);
				}
			}
			jyCase.setCaseCategorys(categoryList);
		}
	}
}
