package com.kang.mapper;

import com.kang.bean.Route;
import com.kang.bean.RouteImg;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface RouteImgMapper {
    @Delete({
        "delete from tab_route_img",
        "where rgid = #{rgid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rgid);

    @Insert({
        "insert into tab_route_img (rgid, rid, ",
        "bigPic, smallPic)",
        "values (#{rgid,jdbcType=INTEGER}, #{rid,jdbcType=INTEGER}, ",
        "#{bigpic,jdbcType=VARCHAR}, #{smallpic,jdbcType=VARCHAR})"
    })
    int insert(RouteImg record);

    @Select({
        "select",
        "rgid, rid, bigPic, smallPic",
        "from tab_route_img",
        "where rgid = #{rgid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="rgid", property="rgid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER),
        @Result(column="bigPic", property="bigpic", jdbcType=JdbcType.VARCHAR),
        @Result(column="smallPic", property="smallpic", jdbcType=JdbcType.VARCHAR)
    })
    RouteImg selectByPrimaryKey(Integer rgid);

    @Select({
        "select",
        "rgid, rid, bigPic, smallPic",
        "from tab_route_img"
    })
    @Results({
        @Result(column="rgid", property="rgid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER),
        @Result(column="bigPic", property="bigpic", jdbcType=JdbcType.VARCHAR),
        @Result(column="smallPic", property="smallpic", jdbcType=JdbcType.VARCHAR)
    })
    List<RouteImg> selectAll();

    @Update({
        "update tab_route_img",
        "set rid = #{rid,jdbcType=INTEGER},",
          "bigPic = #{bigpic,jdbcType=VARCHAR},",
          "smallPic = #{smallpic,jdbcType=VARCHAR}",
        "where rgid = #{rgid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RouteImg record);

    @Select({
            "select",
            "rgid, rid, bigPic, smallPic",
            "from tab_route_img where rid = #{rid}"
    })
    @Results({
            @Result(column="rgid", property="rgid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER),
            @Result(column="bigPic", property="bigpic", jdbcType=JdbcType.VARCHAR),
            @Result(column="smallPic", property="smallpic", jdbcType=JdbcType.VARCHAR)
    })
    List<RouteImg> selectAllByRid(Integer rid);
}