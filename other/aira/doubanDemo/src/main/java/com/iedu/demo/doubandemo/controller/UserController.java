package com.iedu.demo.doubandemo.controller;

import com.iedu.demo.doubandemo.entity.User;
import com.iedu.demo.doubandemo.service.UserService;
import com.iedu.demo.doubandemo.tools.Message;
import com.iedu.demo.doubandemo.tools.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
*   Controller层作用
*   1. 接受用户输入信息
*   2. 调用Service层(处理业务逻辑)
*   3. 接受Service返回数据并根据返回结果进行数据组装或者页面跳转
* */
@Controller
@RequestMapping("/logic/user")
@SessionAttributes("currentUser")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login")
    @ResponseBody
    public Message login(String uid, String pwd, Model model){
        System.out.println("uid: " + uid +", pwd:" + pwd);

        User currentUser = service.login(uid, pwd);
        Message message = new Message();
        System.out.println(currentUser);

        if(currentUser != null) {
            model.addAttribute("currentUser", currentUser);
//            return "redirect:/html/mainPage.html";
            message.setContent("登陆成功");
            message.setError(false);
        }else {
            message.setContent("登陆失败");
            message.setError(true);
//            return "redirect:/html/login.html";
        }
        return message;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public User addUser(User user){

        User newUser = service.addUser(user);

        return newUser;

    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public TableData search(User user, int page, int limit){
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
    public Message pwdReset(int id){
        Message msg = new Message();

        msg.setError(true);
        msg.setContent("服务器错误");

        if(service.resetPwd(id)){
            msg.setError(false);
            msg.setContent("密码已更新");
        }
        return msg;
    }

    @RequestMapping("/currentUser")
    @ResponseBody
    public User currentUser(Model model){
        User currentUser = (User) model.getAttribute("currentUser");
        System.out.println(model  + "------------------------------------model");
        if(currentUser != null){
            return currentUser;
        }

        return currentUser;
    }
}
