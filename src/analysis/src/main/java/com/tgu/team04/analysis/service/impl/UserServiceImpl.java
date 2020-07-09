package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.UserMapper;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.User;
import com.tgu.team04.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public TableData login(String uid, String pwd) {

        User user = mapper.login(uid,pwd);
        TableData data = new TableData();
        if (user != null && user.getState()==1){
            data.setCode(1000);
            data.setMsg("登录成功");
            return data;
        }

        data.setCode(2000);
        data.setMsg("登录失败");
        return data;
    }

    @Override
    public TableData register(User user) {

            TableData data = new TableData();
        if (mapper.selectByUid(user.getUid()) != null){
            data.setCode(2000);
            data.setMsg("用户名已存在");
            return data;
        }

//        if (user.getAge() <= 10 || user.getAge() >= 70){
//            user.setState(0);
//            return user;
//        }
//
//        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
//        Pattern pattern;
//        Matcher matcher;
//        pattern = Pattern.compile(rule);
//        matcher = pattern.matcher(user.getEmail());
//        if (user.getEmail()!=null && !matcher.matches()){
//            user.setState(0);
//            return user;
//        }


        user.setState(1);
        mapper.register(user);
        data.setMsg("注册成功");
        data.setCode(1000);
        return data;

    }
}

