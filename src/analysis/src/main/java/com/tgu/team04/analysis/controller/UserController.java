package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.OneData;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.User;
import com.tgu.team04.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping(value="/search")
    @ResponseBody
    public TableData search(User user, int page, int limit){
        TableData data = new TableData();
        List<User> res =service.search(user, page, limit);
        data.setCode(1000);
        data.setMsg("");
        data.setData(res);

        return data;
    }

    @RequestMapping(value = "pwdreset")
    @ResponseBody
    public OneData pwdReset(int id){
        OneData data = new OneData();

            data.setCode(2000);
            data.setMsg("修改失败");
            data.setData(null);

            if (service.pwdReset(id)==true){
                data.setCode(1000);
                data.setMsg("修改成功");
            }

        return data;
    }

}


