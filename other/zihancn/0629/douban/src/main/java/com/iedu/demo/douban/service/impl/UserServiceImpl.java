package com.iedu.demo.douban.service.impl;

import com.iedu.demo.douban.dao.UserMapper;
import com.iedu.demo.douban.entity.User;
import com.iedu.demo.douban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User login(String uid, String pwd) {

        List result = mapper.selectAll();

        System.out.println(result!=null?result.size():"-1");

        if ("admin".equals(uid)&&"admin".equals(pwd))
            return new User(uid,pwd);
        return null;
    }
}
