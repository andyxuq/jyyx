package com.jyyx.dao.mysql.entity;

import java.util.Date;

public class ProductCategory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category.updated_time
     *
     * @mbggenerated
     */
    private Date updatedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category.category_name
     *
     * @mbggenerated
     */
    private String categoryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category.order_code
     *
     * @mbggenerated
     */
    private Integer orderCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_product_category.parent_id
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category.id
     *
     * @return the value of jy_product_category.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category.id
     *
     * @param id the value for jy_product_category.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category.create_time
     *
     * @return the value of jy_product_category.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category.create_time
     *
     * @param createTime the value for jy_product_category.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category.updated_time
     *
     * @return the value of jy_product_category.updated_time
     *
     * @mbggenerated
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category.updated_time
     *
     * @param updatedTime the value for jy_product_category.updated_time
     *
     * @mbggenerated
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category.category_name
     *
     * @return the value of jy_product_category.category_name
     *
     * @mbggenerated
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category.category_name
     *
     * @param categoryName the value for jy_product_category.category_name
     *
     * @mbggenerated
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category.order_code
     *
     * @return the value of jy_product_category.order_code
     *
     * @mbggenerated
     */
    public Integer getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category.order_code
     *
     * @param orderCode the value for jy_product_category.order_code
     *
     * @mbggenerated
     */
    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_product_category.parent_id
     *
     * @return the value of jy_product_category.parent_id
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_product_category.parent_id
     *
     * @param parentId the value for jy_product_category.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}