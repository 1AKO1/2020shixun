package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user where uid = #{uid} and pwd = #{pwd}")
    public User login(@Param("uid") String uid, @Param("pwd") String pwd);

    @Select("select * from user where uid = #{uid}")
    public  User selectByUid(@Param("uid") String uid);

    @Insert("insert into user( uid,pwd,nick_name,email,address,sex,age,state) "+
            "values(#{user.uid},#{user.pwd},#{user.nickName},#{user.email},#{user.address},#{user.sex},#{user.age},#{user.state})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int register(@Param("user") User user);

    @Update("update user set pwd = #{newPwd} where id=#{id}")
    int updatePwd(@Param("id") int id, @Param("nwePwd") String newPwd);

    @Select("<script>" +
            "select id,uid,nick_name,state" +
            "from user" +
            "<where>" +
            "   <if test='user.uid !=null and user.uid.length > 0'>" +
            "       and uid like #{user.uid}" +
            "   </if>"+
            "   <if test='user.nickName !=null and user.nickName.length > 0'>" +
            "       and nick_name like #{user.nickName}" +
            "   </if>"+
            "   <if test='user.state !=null and user.state != -1'>" +
            "       and state = #{user.state}" +
            "   </if>"+
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "limit #{start}, #{limit}" +
            "</if>"+
            "</script>")
    public List<User> selectByWhere(@Param("user") User user,@Param("start") Integer start,@Param("limit") Integer limit);

    @Select("<script>" +
            "select count(1)" +
            "from user" +
            "<where>" +
            "   <if test='user.uid !=null and user.uid.length > 0'>" +
            "       and uid like #{user.uid}" +
            "   </if>"+
            "   <if test='user.nickName !=null and user.nickName.length > 0'>" +
            "       and nick_name like #{user.nickName}" +
            "   </if>"+
            "   <if test='user.state !=null and user.state != -1'>" +
            "       and state = #{user.state}" +
            "   </if>"+
            "</where>" +
            "</script>")
    public int countSeletcByWhere(@Param("user") User user);
}

