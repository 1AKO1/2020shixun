package com.tesla.demo.douban.service.impl;

import com.tesla.demo.douban.dao.UserMapper;
import com.tesla.demo.douban.entity.User;
import com.tesla.demo.douban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // 需要使用MyBatis（实例化具体的类，属于ORM框架，即将Java与SQL建立联系） + Spring（装配）
    @Autowired
    private UserMapper mapper;

    @Override
    public User login(String uid, String pwd) {

        List result = mapper.selectAll();

        System.out.println(result!=null?result.size():"-1");

        if("admin".equals(uid) && "admin".equals(pwd))
            return new User(uid, pwd);
        else
            return null;
    }
}
