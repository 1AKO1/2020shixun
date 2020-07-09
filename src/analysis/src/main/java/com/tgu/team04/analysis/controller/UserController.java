package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.LogMessage;
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
    @ResponseBody
    public LogMessage login(String uid, String pwd){
        LogMessage logMessage =service.login(uid, pwd);
        return logMessage;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public User register(User user){
        User newUser = service.register(user);
        return newUser;
    }
}


