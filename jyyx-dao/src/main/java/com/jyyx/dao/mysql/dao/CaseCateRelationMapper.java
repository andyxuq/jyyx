package com.jyyx.dao.mysql.dao;

import com.jyyx.dao.mysql.entity.CaseCateRelation;
import com.jyyx.dao.mysql.entity.CaseCateRelationExample;
import com.jyyx.dao.mysql.entity.CaseCateRelationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseCateRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int countByExample(CaseCateRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int deleteByExample(CaseCateRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(CaseCateRelationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int insert(CaseCateRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int insertSelective(CaseCateRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    List<CaseCateRelation> selectByExample(CaseCateRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    CaseCateRelation selectByPrimaryKey(CaseCateRelationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CaseCateRelation record, @Param("example") CaseCateRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CaseCateRelation record, @Param("example") CaseCateRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CaseCateRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_case_category_relation
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CaseCateRelation record);
}