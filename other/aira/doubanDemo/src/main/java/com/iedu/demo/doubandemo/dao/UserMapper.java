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
}