package com.jyyx.dao.model;

import java.util.ArrayList;
import java.util.List;

import com.jyyx.dao.mysql.entity.Case;
import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.mysql.entity.Product;
import com.jyyx.dao.mysql.entity.ProductCategory;

/**
 * 返回给前端的case对象
 * andy xu
 * 2016年11月9日
 */
public class JyProduct extends Product {

	/** 产品图片 */
	private List<Pic> pics = new ArrayList<Pic>();
	
	/** 产品分类 */
	private List<JyProductCategory> productCategorys = new ArrayList<JyProductCategory>();

	/**
	 * @return the pics
	 */
	public List<Pic> getPics() {
		return pics;
	}

	/**
	 * @param pics the pics to set
	 */
	public void setPics(List<Pic> pics) {
		this.pics = pics;
	}

	/**
	 * @return the productCategorys
	 */
	public List<JyProductCategory> getProductCategorys() {
		return productCategorys;
	}

	/**
	 * @param productCategorys the productCategorys to set
	 */
	public void setProductCategorys(List<JyProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}
}
