package com.tesla.demo.douban.dao;

import com.tesla.demo.douban.entity.User;
//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Mapper
public interface UserMapper {

    @Select("select * from tesla_user")
    public List<User> selectAll();

    @Select("select * from tesla_user where uid = #{uid} and pwd = #{pwd}")
    public User login(@Param("uid") String uid, @Param("pwd") String pwd);

    @Select("select * from user where uid = #{uid}")
    public User selectByUid(@Param("uid") String uid);

    @Insert("insert into user(uid,pwd,nick_name) values(#{uid}, #{pwd}, #{nickName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int addUser(@Param("user") User user);
}
