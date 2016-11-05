package com.jyyx.dao.mysql.dao.extend;

import com.jyyx.dao.mysql.entity.Pic;
import com.jyyx.dao.mysql.entity.PicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PicExtendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int countByExample(PicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int deleteByExample(PicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int insert(Pic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int insertSelective(Pic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    List<Pic> selectByExample(PicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    Pic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Pic record, @Param("example") PicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Pic record, @Param("example") PicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Pic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jy_pic
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Pic record);
}