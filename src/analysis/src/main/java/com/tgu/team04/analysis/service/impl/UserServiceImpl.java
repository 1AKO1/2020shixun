package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.UserMapper;
import com.tgu.team04.analysis.entity.LogMessage;
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
    public LogMessage login(String uid, String pwd) {

        User user = mapper.login(uid,pwd);
        LogMessage logMessage = new LogMessage();
        if (user != null && user.getState()==1){
            logMessage.setCode(1000);
            logMessage.setMes("登录成功");
        }

        logMessage.setCode(2000);
        logMessage.setMes("登陆失败");
        return logMessage;
    }

    @Override
    public User register(User user) {

        if (mapper.selectByUid(user.getUid()) != null){
            user.setState(0);
            return user;
        }

        if (user.getAge() <= 10 || user.getAge() >= 70){
            user.setState(0);
            return user;
        }

        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(user.getEmail());
        if (user.getEmail()!=null && !matcher.matches()){
            user.setState(0);
            return user;
        }


        user.setState(1);
        mapper.register(user);
        return user;

    }
}

