package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.UserMapper;
import com.tgu.team04.analysis.entity.User;
import com.tgu.team04.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User login(String uid, String pwd) {

        User user = mapper.login(uid,pwd);
        if (user != null)
            return user;
        return null;
    }

    @Override
    public User register(User user) {

        if (mapper.selectByUid(user.getUid()) != null){
            user.setId(-40);
            return user;
        }

        if (!user.getPwd().equals(user.getPwd2())){
            user.setId(-30);
            return user;
        }

        if (user.getAge() <= 10 || user.getAge() >= 70){
            user.setId(-10);
            return user;
        }

        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(user.getEmail());
        if (user.getEmail()!=null && !matcher.matches()){
            user.setId(-20);
            return user;
        }



        mapper.register(user);
        return user;

    }
}

