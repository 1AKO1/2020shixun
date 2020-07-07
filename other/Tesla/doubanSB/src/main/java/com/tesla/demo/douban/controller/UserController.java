package com.tesla.demo.douban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/logic/user")
@Controller

public class UserController {

    @RequestMapping("/login")
    public String login(String uid, String pwd, String nickName) {

        System.out.println("欢迎！" + nickName + "，您的用户ID是" + uid + "，请牢记您的密码：" + pwd);

        return "mainPage";
    }
}
