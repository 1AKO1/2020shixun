package com.iedu.demo.doubandemo.controller.aira;

import com.iedu.demo.doubandemo.entity.aira.Comment;
import com.iedu.demo.doubandemo.entity.aira.TableData;
import com.iedu.demo.doubandemo.service.aira.AiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bilibili")
//@SessionAttributes("currentUser")
public class AiraController {

    @Autowired
    private AiraService service;

    @RequestMapping("/commentlist")
    @ResponseBody
    public TableData commentList(Integer page, Integer limit){
        TableData data = new TableData();
        System.out.println("page: " + page + ", limit: " + limit);
        List<Comment> result = service.search((int)page, (int)limit);
        if (result != null){
            data.setCode(1000);
            data.setMsg("查询成功");
            data.setData(result);
        }else {
            data.setCode(2000);
            data.setMsg("查询失败");
            data.setData(null);
        }
        return data;
    }
}
