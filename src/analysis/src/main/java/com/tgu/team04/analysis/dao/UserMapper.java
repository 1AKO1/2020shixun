package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    @Select("select * from user where uid = #{uid} and pwd = #{pwd}")
    public User login(@Param("uid") String uid, @Param("pwd") String pwd);

    @Select("select * from user where uid = #{uid}")
    public  User selectByUid(@Param("uid") String uid);

    @Insert("insert into user( uid,pwd,nick_name,email,address,sex,age,state) values(#{user.uid},#{user.pwd},#{user.nickName},#{user.email},#{user.address},#{user.sex},#{user.age},#{user.state})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int register(@Param("user") User user);
}

