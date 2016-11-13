package com.jyyx.dao.model;

import java.util.ArrayList;
import java.util.List;

import com.jyyx.dao.mysql.entity.Case;
import com.jyyx.dao.mysql.entity.Pic;

/**
 * 返回给前端的product对象
 * andy xu
 * 2016年11月9日
 */
public class JyCase extends Case {

	/** 产品图片 */
	private List<Pic> pics = new ArrayList<Pic>();
	
	/** 产品分类 */
	private List<JyCaseCategory> caseCategorys = new ArrayList<JyCaseCategory>();

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
	 * @return the caseCategorys
	 */
	public List<JyCaseCategory> getCaseCategorys() {
		return caseCategorys;
	}

	/**
	 * @param caseCategorys the caseCategorys to set
	 */
	public void setCaseCategorys(List<JyCaseCategory> caseCategorys) {
		this.caseCategorys = caseCategorys;
	}

}
