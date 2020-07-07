package com.iedu.demo.douban.controller;

import com.iedu.demo.douban.entity.User;
import com.iedu.demo.douban.service.UserService;
import com.iedu.demo.douban.tools.Message;
import com.iedu.demo.douban.tools.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; //调用类网络化
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes("currentUser")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login")
    public String login(String uid, String pwd, String nickname, Model model) {

       User currentUser = service.login(uid,pwd);
       if (currentUser != null){
            model.addAttribute("currentUser",currentUser);
            return "redirect:/html/mainPage.html";
       }

//        System.out.println(uid + ',' + pwd + ',' + nickname);

        return "redirect:/html/login.html";
    }

    @RequestMapping("/currentUser")
    @ResponseBody
    public User currentUser(Model model){
        User currentUser = (User) model.getAttribute("currentUser");
        if (currentUser != null)
            return currentUser;
        return null;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public User addUser(User user) {

        User newUser = service.addUser(user);

        return newUser;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(User user, int page, int limit) {

        TableData data = new TableData();
        List<User> result = service.search(user, page, limit);
        data.setCode(0);
        data.setMsg("");
        data.setCount(service.searchCount(user));
        data.setData(result);

        return data;
    }

    @RequestMapping(value = "/pwdreset")
    @ResponseBody
    public Object pwdReset(int id) {

        Message msg = new Message();

        msg.setError(true);
        msg.setContent("服务器错误");

        if (service.resetPwd(id)) {
            msg.setError(false);
            msg.setContent("密码已更新");
        }
        return null;
    }

}
/*
  redirect:重定向
 */