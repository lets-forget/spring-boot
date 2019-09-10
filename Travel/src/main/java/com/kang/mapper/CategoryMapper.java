package com.kang.mapper;

import com.kang.bean.Category;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CategoryMapper {
    @Delete({
        "delete from tab_category",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cid);

    @Insert({
        "insert into tab_category (cid, cname)",
        "values (#{cid,jdbcType=INTEGER}, #{cname,jdbcType=VARCHAR})"
    })
    int insert(Category record);

    @Select({
        "select",
        "cid, cname",
        "from tab_category",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cname", property="cname", jdbcType=JdbcType.VARCHAR)
    })
    Category selectByPrimaryKey(Integer cid);

    @Select({
        "select",
        "cid, cname",
        "from tab_category ORDER BY cid asc"
    })
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cname", property="cname", jdbcType=JdbcType.VARCHAR)
    })
    List<Category> selectAll();

    @Update({
        "update tab_category",
        "set cname = #{cname,jdbcType=VARCHAR}",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);
}