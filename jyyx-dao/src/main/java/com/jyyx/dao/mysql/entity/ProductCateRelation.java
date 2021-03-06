package com.jyyx.dao.mysql.entity;

import java.util.Date;

public class ProductCateRelation extends ProductCateRelationKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category_relation.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category_relation.order_code
     *
     * @mbggenerated
     */
    private Integer orderCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category_relation.create_time
     *
     * @return the value of jy_product_category_relation.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category_relation.create_time
     *
     * @param createTime the value for jy_product_category_relation.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category_relation.order_code
     *
     * @return the value of jy_product_category_relation.order_code
     *
     * @mbggenerated
     */
    public Integer getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category_relation.order_code
     *
     * @param orderCode the value for jy_product_category_relation.order_code
     *
     * @mbggenerated
     */
    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }
}