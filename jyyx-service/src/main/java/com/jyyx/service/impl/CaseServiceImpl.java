package com.jyyx.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.dao.CaseDao;
import com.jyyx.dao.model.CaseParam;
import com.jyyx.dao.model.JyCase;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;
import com.jyyx.service.CaseService;

/**
 * andy xu
 * 2016年11月12日
 */
@Service
public class CaseServiceImpl implements CaseService {

	@Autowired
	private CaseDao caseDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.CaseService#addResource(com.jyyx.dao.model.CaseParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addResource(CaseParam caseParam) throws BeansException, InstantiationException, IllegalAccessException {
		caseDao.addResources(caseParam);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.CaseService#modifyResources(com.jyyx.dao.model.CaseParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResources(CaseParam caseParam) throws BeansException, InstantiationException, IllegalAccessException {
		caseDao.modifyResources(caseParam);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.CaseService#getResources(com.jyyx.dao.model.CaseParam)
	 */
	public List<JyCase> getResources(CaseParam caseParam) throws BeansException, InstantiationException, IllegalAccessException {
		return caseDao.getResources(caseParam);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.CaseService#getPicResourcesWithPage(com.jyyx.dao.model.CaseParam, int, int)
	 */
	public PageData<JyCase> getResourcesWithPage(CaseParam caseParam, int page, int pageRow) throws BeansException, InstantiationException, IllegalAccessException {
		int totalCount = caseDao.getResourcesCount(caseParam);
		PageInfo pageInfo = new PageInfo(page, pageRow, totalCount);
		
		return caseDao.getResourcesWithPage(caseParam, pageInfo);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.CaseService#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		caseDao.deleteResources(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.CaseService#getResourceById(int)
	 */
	public JyCase getResourceById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException {
		return caseDao.getResourcesById(resourceId);
	}
	
}
