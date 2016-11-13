package com.jyyx.dao;

import com.jyyx.dao.mysql.entity.CaseCateRelation;
import com.jyyx.dao.mysql.entity.ProductCateRelation;

/**
 * 案例-分类关系dao接口
 * andy xu
 * 2016年11月9日
 */
public interface CaseCateRelationDao {

	/** 删除资源  */
	void deleteResources(int caseId, int categoryId);
	
	/** 根据资源ID删除关系 */
	void deleteResourcesByResId(int caseId);
	
	/** 添加资源 */
	void addResources(CaseCateRelation caseCate);
	
	/** 修改资源 */
	void modifyResources(CaseCateRelation caseCate);
}
