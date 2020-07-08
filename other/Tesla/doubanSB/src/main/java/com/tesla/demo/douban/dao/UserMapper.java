package com.tesla.demo.douban.dao;

import com.tesla.demo.douban.entity.User;
//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Mapper
public interface UserMapper {

    @Select("select * from tesla_user")
    public List<User> selectAll();
}
