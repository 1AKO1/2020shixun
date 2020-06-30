package com.iedu.demo.douban.dao;

import com.iedu.demo.douban.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from zhihan_user")
    public List<User> selectAll();
}
