package com.jyyx.dao.mysql.dao.extend;

import com.jyyx.dao.model.CaseParam;
import com.jyyx.dao.model.JyCase;
import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.mysql.entity.Case;

import java.util.List;

public interface CaseExtendMapper {

	/**
	 * 查询案例总数
	 */
    int countByParam(CaseParam param);
    
    /**
     * 查询案例
     * @param param
     * @return
     */
    List<JyCase> selectByParam(CaseParam param);
    
    /**
     * 插入案例
     * @param product
     */
    void insertSelective(Case caseData);

}