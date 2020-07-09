package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.TableData;
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
    public TableData login(String uid, String pwd){
        TableData data =service.login(uid, pwd);
        return data;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public TableData register(User user){
        TableData data = service.register(user);
        return data;
    }
}


