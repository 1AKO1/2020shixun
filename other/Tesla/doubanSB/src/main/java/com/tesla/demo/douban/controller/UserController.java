package com.tesla.demo.douban.controller;

import com.tesla.demo.douban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logic/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login")
    public String login(String uid, String pwd, String nickName) {

        if(service.login(uid, pwd) != null)
            return "redirect:/html/mainPage.html";
        else
            return "redirect:/html/login.html";
    }

//    @RequestMapping(value = "/add")
//    @RequestBody
//    public User addUser(User user) {
//
//        User newUser = service.addUser(user);
//
//        return newUser;
//    }
}
