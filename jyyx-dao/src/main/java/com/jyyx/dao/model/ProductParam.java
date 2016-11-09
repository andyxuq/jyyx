package com.jyyx.dao.model;

import java.util.List;

import com.jyyx.dao.mysql.entity.ProductCategory;

/**
 * 产品查询参数
 * andy xu
 * 2016年11月9日
 */
public class ProductParam {
	
	/** 产品ID */
	private int id;
	
	/** 产品名 */
    private String productName;

    /** 产品链接 */
    private String productLink;

    /** 产品描述 */
    private String productDesc;
    
    /** 产品分类 */
    private List<ProductCategory> productCategorys;

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productLink
	 */
	public String getProductLink() {
		return productLink;
	}

	/**
	 * @param productLink the productLink to set
	 */
	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the productCategorys
	 */
	public List<ProductCategory> getProductCategorys() {
		return productCategorys;
	}

	/**
	 * @param productCategorys the productCategorys to set
	 */
	public void setProductCategorys(List<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}
}
