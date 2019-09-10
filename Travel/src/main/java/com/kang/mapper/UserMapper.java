package com.kang.mapper;

import com.kang.bean.User;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from tab_user",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer uid);

    @Insert({
        "insert into tab_user (username, ",
        "password, name, ",
        "birthday, sex, telephone, ",
        "email, status, code)",
        "values (#{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{birthday,jdbcType=DATE}, #{sex,jdbcType=CHAR}, #{telephone,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{status,jdbcType=CHAR}, #{code,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @Select({
        "select",
        "uid, username, password, name, birthday, sex, telephone, email, status, code",
        "from tab_user",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer uid);

    @Select({
        "select",
        "uid, username, password, name, birthday, sex, telephone, email, status, code",
        "from tab_user"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update tab_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=DATE},",
          "sex = #{sex,jdbcType=CHAR},",
          "telephone = #{telephone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=CHAR},",
          "code = #{code,jdbcType=VARCHAR}",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "select",
            "uid, username, password, name, birthday, sex, telephone, email, status, code",
            "from tab_user",
            "where username = #{username,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
            @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
            @Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)
    })
    User selectByName(String username);

    @Update({
        "update tab_user set status = #{status} where code = #{code}"
    })
    int updatecheckCode(@Param("code") String code,@Param("status")int status);
}