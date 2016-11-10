package com.jyyx.dao.mysql.dao.extend;

import com.jyyx.dao.model.JyProduct;
import com.jyyx.dao.model.ProductParam;
import com.jyyx.dao.mysql.entity.Product;

import java.util.List;

public interface ProductExtendMapper {

	/**
	 * 查询产品总数
	 */
    int countByParam(ProductParam param);
    
    /**
     * 查询产品
     * @param param
     * @return
     */
    List<JyProduct> selectByParam(ProductParam param);
    
    /**
     * 插入产品
     * @param product
     */
    void insertSelective(Product product);

}