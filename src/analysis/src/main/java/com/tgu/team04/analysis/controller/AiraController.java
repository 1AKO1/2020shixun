package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.AiraComment;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.service.AiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        List<AiraComment> result = service.search((int)page, (int)limit);

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
