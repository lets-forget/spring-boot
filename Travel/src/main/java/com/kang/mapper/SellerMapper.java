package com.kang.mapper;

import com.kang.bean.Seller;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface SellerMapper {
    @Delete({
        "delete from tab_seller",
        "where sid = #{sid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer sid);

    @Insert({
        "insert into tab_seller (sid, sname, ",
        "consphone, address)",
        "values (#{sid,jdbcType=INTEGER}, #{sname,jdbcType=VARCHAR}, ",
        "#{consphone,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR})"
    })
    int insert(Seller record);

    @Select({
        "select",
        "sid, sname, consphone, address",
        "from tab_seller",
        "where sid = #{sid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="sname", property="sname", jdbcType=JdbcType.VARCHAR),
        @Result(column="consphone", property="consphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    Seller selectByPrimaryKey(Integer sid);

    @Select({
        "select",
        "sid, sname, consphone, address",
        "from tab_seller"
    })
    @Results({
        @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="sname", property="sname", jdbcType=JdbcType.VARCHAR),
        @Result(column="consphone", property="consphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    List<Seller> selectAll();

    @Update({
        "update tab_seller",
        "set sname = #{sname,jdbcType=VARCHAR},",
          "consphone = #{consphone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR}",
        "where sid = #{sid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Seller record);
}