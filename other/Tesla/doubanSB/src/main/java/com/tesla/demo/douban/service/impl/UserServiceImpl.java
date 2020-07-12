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

        User user = mapper.login(uid, pwd);
        if (user != null)
            return user;
        else
            return null;
    }

//    @Override
//    public User addUser(User user) {
//
//        if(mapper.selectByUid(user.getUid())==null) {
//            mapper.addUser(user);
//            return user;
//        }
//        else
//            return null;
//    }
}
