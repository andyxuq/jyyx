package com.jyyx.dao.mysql.entity;

import java.util.Date;

public class Msg {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.updated_time
     *
     * @mbggenerated
     */
    private Date updatedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.msg_title
     *
     * @mbggenerated
     */
    private String msgTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.order_code
     *
     * @mbggenerated
     */
    private Integer orderCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.category_id
     *
     * @mbggenerated
     */
    private Integer categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.visit_count
     *
     * @mbggenerated
     */
    private Integer visitCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jy_msg.msg_content
     *
     * @mbggenerated
     */
    private String msgContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.id
     *
     * @return the value of jy_msg.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.id
     *
     * @param id the value for jy_msg.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.create_time
     *
     * @return the value of jy_msg.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.create_time
     *
     * @param createTime the value for jy_msg.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.updated_time
     *
     * @return the value of jy_msg.updated_time
     *
     * @mbggenerated
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.updated_time
     *
     * @param updatedTime the value for jy_msg.updated_time
     *
     * @mbggenerated
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.msg_title
     *
     * @return the value of jy_msg.msg_title
     *
     * @mbggenerated
     */
    public String getMsgTitle() {
        return msgTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.msg_title
     *
     * @param msgTitle the value for jy_msg.msg_title
     *
     * @mbggenerated
     */
    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.order_code
     *
     * @return the value of jy_msg.order_code
     *
     * @mbggenerated
     */
    public Integer getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.order_code
     *
     * @param orderCode the value for jy_msg.order_code
     *
     * @mbggenerated
     */
    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.category_id
     *
     * @return the value of jy_msg.category_id
     *
     * @mbggenerated
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.category_id
     *
     * @param categoryId the value for jy_msg.category_id
     *
     * @mbggenerated
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.visit_count
     *
     * @return the value of jy_msg.visit_count
     *
     * @mbggenerated
     */
    public Integer getVisitCount() {
        return visitCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.visit_count
     *
     * @param visitCount the value for jy_msg.visit_count
     *
     * @mbggenerated
     */
    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jy_msg.msg_content
     *
     * @return the value of jy_msg.msg_content
     *
     * @mbggenerated
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jy_msg.msg_content
     *
     * @param msgContent the value for jy_msg.msg_content
     *
     * @mbggenerated
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}