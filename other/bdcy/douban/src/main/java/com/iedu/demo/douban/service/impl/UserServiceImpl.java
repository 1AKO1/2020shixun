package com.iedu.demo.douban.service.impl;

import com.iedu.demo.douban.dao.UserMapper;
import com.iedu.demo.douban.entity.User;
import com.iedu.demo.douban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper mapper;

    @Override
    public User login(String uid, String pwd){
        User user = mapper.login(uid,pwd);
        if(user != null)
            return user;
        return null;
    }
    @Override
    public User addUser(User user){

        if(mapper.selectByUid(user.getUid())==null){
            mapper.addUser(user);
            return user;
        }
        return null;
    }

}
