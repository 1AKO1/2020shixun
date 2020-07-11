package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.Music;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.User;
import com.tgu.team04.analysis.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService service;

    @RequestMapping("/search")
    @ResponseBody
    public TableData search(String name,String singere,String time , String tag,String kind, int page, int limit){
        TableData data = new TableData();
        List<Music> res =service.search(name, singere, time, tag, kind, page, limit);
        data.setCode(1000);
        data.setMsg("");
        data.setData(res);
        data.setCount(service.searchCount(name, singere, time, tag, kind));
        return data;
    }
}
