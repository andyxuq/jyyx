package com.jyyx.dao.model;

import java.util.List;

import com.jyyx.dao.mysql.entity.CaseCateRelation;
import com.jyyx.dao.mysql.entity.ProductCateRelation;
import com.jyyx.dao.mysql.entity.ProductCategory;

/**
 * 案例查询参数
 * andy xu
 * 2016年11月9日
 */
public class CaseParam {
	
	/** 案例ID */
	private int id;
	
	/** 案例名 */
    private String caseName;
    
    /** 案例描述 */
    private String caseDesc;
    
    /** 案例价格 */
    private double casePrice;
    
    /** 案例主题 */
    private String caseTips;

    /** 产品分类 */
    private List<CaseCateRelation> caseCategorys;

	/** 开始行数 */
	private int startRow;
	
	/** 每页显示行数 */
	private int pageRow;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the caseName
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * @param caseName the caseName to set
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	/**
	 * @return the caseCategorys
	 */
	public List<CaseCateRelation> getCaseCategorys() {
		return caseCategorys;
	}

	/**
	 * @param caseCategorys the caseCategorys to set
	 */
	public void setCaseCategorys(List<CaseCateRelation> caseCategorys) {
		this.caseCategorys = caseCategorys;
	}

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the pageRow
	 */
	public int getPageRow() {
		return pageRow;
	}

	/**
	 * @param pageRow the pageRow to set
	 */
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	/**
	 * @return the caseDesc
	 */
	public String getCaseDesc() {
		return caseDesc;
	}

	/**
	 * @param caseDesc the caseDesc to set
	 */
	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	/**
	 * @return the casePrice
	 */
	public double getCasePrice() {
		return casePrice;
	}

	/**
	 * @param casePrice the casePrice to set
	 */
	public void setCasePrice(double casePrice) {
		this.casePrice = casePrice;
	}

	/**
	 * @return the caseTips
	 */
	public String getCaseTips() {
		return caseTips;
	}

	/**
	 * @param caseTips the caseTips to set
	 */
	public void setCaseTips(String caseTips) {
		this.caseTips = caseTips;
	}
}
