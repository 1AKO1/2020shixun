package com.iedu.demo.douban.controller;

import com.iedu.demo.douban.entity.User;
import com.iedu.demo.douban.service.UserService;
import com.iedu.demo.douban.tools.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; //调用类网络化
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.openmbean.TabularData;
import javax.swing.text.TabableView;
import java.util.List;

/**
 * 1.接受用户输入信息
 * 2.调用Service层(调用业务逻辑)
 * 3.接收Service返回数据并返回结果进行数据组装或页面跳转
 */

@Controller
@RequestMapping("/logic/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login")
    public String login(String uid, String pwd, String nickname) {

        if(service.login(uid,pwd) != null)
            return "redirect:/html/mainPage.html";

//        System.out.println(uid + ',' + pwd + ',' + nickname);

        return "redirect:/html/login.html";
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public User addUser(User user){

        User newUser = service.addUser(user);

        return newUser;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(User user){

        TableData data = new TableData();
        List<User> result = service.search(user);
        data.setCode(0);
        data.setMsg("");
        data.setCount(result.size());
        data.setData(result);

        return data;
    }

}
/*
  redirect:重定向
 */