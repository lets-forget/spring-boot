package com.kang.mapper;

import com.kang.bean.Route;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


public interface RouteMapper {
    @Delete({
        "delete from tab_route",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rid);

    @Insert({
        "insert into tab_route (rid, rname, ",
        "price, routeIntroduce, ",
        "rflag, rdate, isThemeTour, ",
        "count, cid, rimage, ",
        "sid, sourceId)",
        "values (#{rid,jdbcType=INTEGER}, #{rname,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=DOUBLE}, #{routeintroduce,jdbcType=VARCHAR}, ",
        "#{rflag,jdbcType=CHAR}, #{rdate,jdbcType=VARCHAR}, #{isthemetour,jdbcType=CHAR}, ",
        "#{count,jdbcType=INTEGER}, #{cid,jdbcType=INTEGER}, #{rimage,jdbcType=VARCHAR}, ",
        "#{sid,jdbcType=INTEGER}, #{sourceid,jdbcType=VARCHAR})"
    })
    int insert(Route record);

    @Select({
        "select",
        "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
        "sid, sourceId",
        "from tab_route",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
        @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
        @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
        @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
        @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
        @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    Route selectByPrimaryKey(Integer cid);

    @Select({
            "<script>",
        "select",
        "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
        "sid, sourceId",
        "from tab_route <where> <if test='cid != 0 '> and cid=#{cid} </if> <if test=' rname != null '> and rname like concat('%',#{rname},'%') </if> </where> ",
            "</script>",
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
        @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
        @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
        @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
        @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
        @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    List<Route> selectAll(@Param("cid") Integer cid,@Param("rname") String rname);

    @Update({
        "update tab_route",
        "set rname = #{rname,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DOUBLE},",
          "routeIntroduce = #{routeintroduce,jdbcType=VARCHAR},",
          "rflag = #{rflag,jdbcType=CHAR},",
          "rdate = #{rdate,jdbcType=VARCHAR},",
          "isThemeTour = #{isthemetour,jdbcType=CHAR},",
          "count = #{count,jdbcType=INTEGER},",
          "cid = #{cid,jdbcType=INTEGER},",
          "rimage = #{rimage,jdbcType=VARCHAR},",
          "sid = #{sid,jdbcType=INTEGER},",
          "sourceId = #{sourceid,jdbcType=VARCHAR}",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Route record);

    @Select({
            "select",
            "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
            "sid, sourceId",
            "from tab_route order by rdate desc limit 0,6"
    })
    @Results({
            @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
            @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
            @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
            @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
            @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
            @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
            @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    List<Route> selectDescAll();

    @Select({
            "select",
            "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
            "sid, sourceId",
            "from tab_route order by count desc limit 0,6"
    })
    @Results({
            @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
            @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
            @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
            @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
            @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
            @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
            @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    List<Route> selectCountAll();

    @Select({
            "select",
            "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
            "sid, sourceId",
            "from tab_route order by price desc limit 0,4"
    })
    @Results({
            @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
            @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
            @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
            @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
            @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
            @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
            @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    List<Route> selectthemeAll();


    @Select({
            "select",
            "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
            "sid, sourceId",
            "from tab_route",
            "where rid = #{rid,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
            @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
            @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
            @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
            @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
            @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
            @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    Route selectByRid(Integer rid);


    @Update({
            "update tab_route",
            "set count=count+1 ",
            "where rid = #{rid,jdbcType=INTEGER}"
    })
    Integer updateByCountByRid(Integer rid);

    @Select({
            "select",
            "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
            "sid, sourceId",
            "from tab_route order by count desc"
    })
    @Results({
            @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
            @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
            @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
            @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
            @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
            @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
            @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    List<Route> selectListCount();


    @Select({
            "<script>",
            "select",
            "rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, ",
            "sid, sourceId",
            "from tab_route   <if test=' list !=null and list.size()>0'> <foreach collection=\"list\" index=\"index\"  item=\"id\" open=\"where rid in (\" separator=\",\" close=\")\"> #{id} </foreach> </if> <if test=' list.size() lte 0'> where  1!=1 </if> ",
            "</script>",
    })
    @Results({
            @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="routeIntroduce", property="routeintroduce", jdbcType=JdbcType.VARCHAR),
            @Result(column="rflag", property="rflag", jdbcType=JdbcType.CHAR),
            @Result(column="rdate", property="rdate", jdbcType=JdbcType.VARCHAR),
            @Result(column="isThemeTour", property="isthemetour", jdbcType=JdbcType.CHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
            @Result(column="rimage", property="rimage", jdbcType=JdbcType.VARCHAR),
            @Result(column="sid", property="sid", jdbcType=JdbcType.INTEGER),
            @Result(column="sourceId", property="sourceid", jdbcType=JdbcType.VARCHAR)
    })
    List<Route> selectByListRid( List<Integer> parmas);

    @Update({
            "update tab_route",
            "set count=count-1 ",
            "where rid = #{rid,jdbcType=INTEGER}"
    })
    Integer deleteFavorirteByRid(Integer rid);
}