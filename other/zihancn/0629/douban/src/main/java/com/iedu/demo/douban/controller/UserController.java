package com.iedu.demo.douban.controller;

import com.iedu.demo.douban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; //调用类网络化

@Controller
@RequestMapping("/logic/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login")
    public String login(String uid, String pwd, String nickname) {

        if(service.login(uid,pwd) != null)
            return "redirect:/mainPage.html";

//        System.out.println(uid + ',' + pwd + ',' + nickname);

        return "redirect:/login.html";
    }
}
/*
  redirect:重定向
 */