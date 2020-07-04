package com.iedu.demo.douban.dao;

import com.iedu.demo.douban.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from zhihan_user limit 0,30")
    public List<User> selectAll();
    @Select("select * from zhihan_user where uid = #{uid} and pwd = #{pwd} and state = 1" )
    public User login(@Param("uid") String uid,@Param("pwd") String pwd);

    @Select("select * from zhihan_user where uid = #{uid}")
    public User selectByUid(@Param("uid") String uid);

    @Insert("insert into zhihan_user(uid,pwd,nick_name,state) values(#{user.uid}, #{user.pwd}, #{user.nickName},#{user.state})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int addUser(@Param("user") User user);
}
