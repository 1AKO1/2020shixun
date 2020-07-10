package com.iedu.demo.doubandemo.dao;

import com.iedu.demo.doubandemo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from aira_user limit 0, 30")
    public List<User> selectAll();

    @Select("select * from aira_user where uid = #{uid} and pwd = #{pwd}")
    public User login(@Param("uid") String uid, @Param("pwd") String pwd);

    @Select("select * from aira_user where uid = #{uid}")
    public User selectByUid(@Param("uid") String uid);

    @Insert("insert into aira_user(uid, pwd, nick_name, state) " +
            "   values (#{user.uid}, #{user.pwd}, #{user.nickName}, #{user.state}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int addUser(@Param("user") User user);

    @Update("update aira_user set pwd = #{newPwd} where id = #{id}")
    public int updatePwd(@Param("id") int id, @Param("newPwd") String newPwd);

    @Select("<script>" +
            "select id, uid, nick_name, state" +
            "   from aira_user" +
            "<where>" +
            "   <if test='user.uid != null and user.uid.length > 0'>" +
            "       AND uid like #{user.uid}" +
            "   </if>" +
            "   <if test='user.nickName != null and user.nickName.length > 0'>" +
            "       AND nick_name like '%${user.nickName}%'" +
            "   </if>" +
            "   <if test='user.state != null and user.state != -1'>" +
            "       AND state = #{user.state}" +
            "   </if>" +
            "</where>" +
            "<if test='start != null and limit != null' >" +
            "   limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    public List<User> selectByWhere(@Param("user") User user,
                                    @Param("start") Integer start,
                                    @Param("limit") Integer limit);


    @Select("<script>" +
            "select count(1)" +
            "   from aira_user" +
            "<where>" +
            "   <if test='user.uid != null and user.uid.length > 0'>" +
            "       AND uid like #{user.uid}" +
            "   </if>" +
            "   <if test='user.nickName != null and user.nickName.length > 0'>" +
            "       AND nick_name like '%${user.nickName}%'" +
            "   </if>" +
            "   <if test='user.state != null and user.state != -1'>" +
            "       AND state = #{user.state}" +
            "   </if>" +
            "</where>" +
            "</script>")
    public int countSelectByWhere(@Param("user") User user);
}