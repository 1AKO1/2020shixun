package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.dangdangBook;
import com.tgu.team04.analysis.entity.dangdangData;
import com.tgu.team04.analysis.service.DangService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dangdang")
public class DangdangController {

    public static float min =0;
    public static float max = 1000;

    @Autowired
    private DangService service;


    @RequestMapping("/search")
    @ResponseBody
    public TableData search(String bookName, String author, String presshouse,String smalllei,
                            float discount,float min,float max,int page, int limit ){
        dangdangBook Book = new dangdangBook();
        Book.setName(bookName.length() == 0 ? null : bookName);
        Book.setAuthor(author.length() == 0 ? null : author);
        Book.setPhouse(presshouse);
        Book.setSamlllei(smalllei);
        Book.setPs(discount);

        this.min=min;
        this.max=max;

        System.out.println(Book);
        System.out.println("page: " + page + ", limit: " + limit);

        TableData dangdangdata = new TableData();
        List<dangdangBook> result = service.searchByOptions(Book ,page,limit);

        if (result != null && result.size() != 0){
            dangdangdata.setCode(1000);
            dangdangdata.setMsg("查询成功");
            dangdangdata.setCount(service.searchCount(Book,min,max));
            dangdangdata.setData(result);
        }else {
            dangdangdata.setCode(2000);
            dangdangdata.setMsg("查询失败");
            dangdangdata.setCount(0);
            dangdangdata.setData(null);
        }
        return dangdangdata;
    }

    @RequestMapping("/dangAnalysis")
    @ResponseBody
    public TableData dangAnalysis(String type){
        TableData data = new TableData();
        List<dangdangData> result = service.dangAnalysis(type);

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
