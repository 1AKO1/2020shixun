package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.User;
import com.tgu.team04.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/logic/user")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login")
    public User login(String uid, String pwd){
        User user =service.login(uid, pwd);
        if (user != null){
            user.setMessage("登录成功");
        }
        else{
            user.setMessage("登陆失败");
        }

        return user;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public User register(User user){
        User newUser = service.register(user);
        return newUser;
    }
}


