package com.kang.mapper;

import com.kang.bean.Favorite;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FavoriteMapper {
    @Delete({
        "delete from tab_favorite",
        "where rid = #{rid,jdbcType=INTEGER}",
          "and uid = #{uid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("rid") Integer rid, @Param("uid") Integer uid);

    @Insert({
        "insert into tab_favorite (rid, uid, ",
        "date)",
        "values (#{rid,jdbcType=INTEGER}, #{uid,jdbcType=INTEGER}, ",
        "#{date,jdbcType=DATE})"
    })
    int insert(Favorite record);

    @Select({
        "select",
        "rid, uid, date",
        "from tab_favorite",
        "where rid = #{rid,jdbcType=INTEGER}",
          "and uid = #{uid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE)
    })
    Favorite selectByPrimaryKey(@Param("rid") Integer rid, @Param("uid") Integer uid);

    @Select({
        "select",
        "rid, uid, date",
        "from tab_favorite where uid = #{uid}"
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE)
    })
    List<Favorite> selectAll(Integer uid);

    @Update({
        "update tab_favorite",
        "set date = #{date,jdbcType=DATE}",
        "where rid = #{rid,jdbcType=INTEGER}",
          "and uid = #{uid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Favorite record);


    @Delete({
            "delete from tab_favorite",
            "where rid = #{rid,jdbcType=INTEGER}",
    })
    Integer deleteByRid(Integer rid);
}