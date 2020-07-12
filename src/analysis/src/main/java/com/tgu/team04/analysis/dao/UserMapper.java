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

    @Update("update user set pwd = #{newPwd} where uid = #{uid}")
    int updatePwd(@Param("uid") String uid, @Param("nwePwd") String newPwd);

    @Select("<script>" +
            "select id,uid,nick_name,state" +
            "from user" +
            "<where>" +
            "   <if test='uid !=null and uid.length > 0'>" +
            "       and uid like #{uid}" +
            "   </if>"+
            "   <if test='nickName !=null and nickName.length > 0'>" +
            "       and nick_name like #{nickName}" +
            "   </if>"+
            "   <if test='state !=null and state != -1'>" +
            "       and state = #{state}" +
            "   </if>"+
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "limit #{start}, #{limit}" +
            "</if>"+
            "</script>")
    public List<User> selectByWhere(@Param("uid") String uid,@Param("nickName") String nickName,@Param("state") Integer state,@Param("start") Integer start,@Param("limit") Integer limit);

    @Select("<script>" +
            "select count(1)" +
            "from user" +
            "<where>" +
            "   <if test='uid !=null and uid.length > 0'>" +
            "       and uid like #{uid}" +
            "   </if>"+
            "   <if test='nickName !=null and nickName.length > 0'>" +
            "       and nick_name like #{nickName}" +
            "   </if>"+
            "   <if test='state !=null and state != -1'>" +
            "       and state = #{state}" +
            "   </if>"+
            "</where>" +
            "</script>")
    public int countSeletcByWhere(@Param("uid") String uid,@Param("nickName") String nickName,@Param("state") Integer state);

    @Update("update user set state = #{state} where uid = #{uid}")
    int stateReset(@Param("uid") String uid, @Param("state") Integer state);
}

