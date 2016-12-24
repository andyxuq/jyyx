package com.jyyx.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyyx.dao.ProductDao;
import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.utils.PageData;
import com.jyyx.dao.utils.PageInfo;
import com.jyyx.service.ProductService;

/**
 * andy xu
 * 2016年11月12日
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductService#addResource(com.jyyx.dao.model.ProductParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addResource(ProductParam productParam) {
		productDao.addResources(productParam);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductService#modifyResources(com.jyyx.dao.model.ProductParam)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void modifyResources(ProductParam productParam) {
		productDao.modifyResources(productParam);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductService#getResources(com.jyyx.dao.model.ProductParam)
	 */
	public List<JyProduct> getResources(ProductParam productParam) throws BeansException, InstantiationException, IllegalAccessException {
		return productDao.getResources(productParam);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductService#getPicResourcesWithPage(com.jyyx.dao.model.ProductParam, int, int)
	 */
	public PageData<JyProduct> getResourcesWithPage(ProductParam productParam, int page, int pageRow) throws BeansException, InstantiationException, IllegalAccessException {
		int totalCount = productDao.getResourcesCount(productParam);
		PageInfo pageInfo = new PageInfo(page, pageRow, totalCount);
		
		return productDao.getResourcesWithPage(productParam, pageInfo);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductService#deleteResources(int)
	 */
	public void deleteResources(int resourceId) {
		productDao.deleteResources(resourceId);
	}

	/* (non-Javadoc)
	 * @see com.jyyx.service.ProductService#getResourceById(int)
	 */
	public JyProduct getResourceById(int resourceId) throws BeansException, InstantiationException, IllegalAccessException {
		return productDao.getResourcesById(resourceId);
	}
	
}
