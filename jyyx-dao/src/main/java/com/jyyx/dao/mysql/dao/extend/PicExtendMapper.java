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
    List<Pic> selectByExample(PicExample example);

}