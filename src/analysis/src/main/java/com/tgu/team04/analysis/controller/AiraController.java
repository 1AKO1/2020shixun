package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.AiraComment;
import com.tgu.team04.analysis.entity.OneData;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.service.AiraService;
import com.tgu.team04.analysis.tools.AiraTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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

    @RequestMapping("/predict")
    @ResponseBody
    public OneData predict(String comment){
        OneData oneData = new OneData();
        int score = service.getScore(comment);
        oneData.setCode(1000);
        oneData.setMsg("查询成功");
        oneData.setData(score);
        return oneData;
    }

    @RequestMapping("/search")
    @ResponseBody
    public TableData search(String uname, String content, int vipStatus, String progress, int page, int limit){
        AiraComment comment = new AiraComment();
        comment.setUname(uname.length() == 0 ? null : uname);
        comment.setContent(content.length() == 0 ? null : content);
        comment.setVipStatus(vipStatus);
        comment.setProgress(progress);
        System.out.println(comment);
        System.out.println("page: " + page + ", limit: " + limit);
        TableData data = new TableData();
        List<AiraComment> result = service.searchByOption(comment, page, limit);
        if (result != null){
            data.setCode(1000);
            data.setMsg("查询成功");
            data.setCount(service.searchCount(comment));
            data.setData(result);
        }
        return data;
    }

}
