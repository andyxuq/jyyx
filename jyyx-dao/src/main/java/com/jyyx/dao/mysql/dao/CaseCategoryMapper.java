package com.jyyx.dao.mysql.dao;

import com.jyyx.dao.mysql.entity.CaseCategory;
import com.jyyx.dao.mysql.entity.CaseCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int countByExample(CaseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int deleteByExample(CaseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int insert(CaseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int insertSelective(CaseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    List<CaseCategory> selectByExample(CaseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    CaseCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CaseCategory record, @Param("example") CaseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CaseCategory record, @Param("example") CaseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CaseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CaseCategory record);
}