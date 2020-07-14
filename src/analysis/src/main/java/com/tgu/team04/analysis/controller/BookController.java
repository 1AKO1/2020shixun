package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.*;
import com.tgu.team04.analysis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/doubanBook")
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping("/search")
    @ResponseBody
    public TableData search(String title, String author, String press,
                                int numOfType, String type, String isbn,
                                int minVotes, float minScore, float maxScore,
                                int page, int limit) {
        Book_SearchData searchData = new Book_SearchData();
        searchData.setTitle(title);
        searchData.setAuthor(author);
        searchData.setPress(press);
        searchData.setNumOfType(numOfType);
        searchData.setType(type);   // 用split函数来完成将前端发送的字符串型type转换为字符串数组，方便后面查询
        searchData.setIsbn(isbn);
        searchData.setMinVotes(minVotes);
        searchData.setMinScore(minScore);
        searchData.setMaxScore(maxScore);

        searchData.setPage(page);
        searchData.setLimit(limit);

        System.out.println(searchData);
        System.out.println("Page: " + page + ", limit: " + limit);

        TableData tableData = new TableData();
        List<Book_ResultData> resultDataList = service.searchByOptions(searchData);
        if (resultDataList != null && resultDataList.size() != 0) {
            tableData.setCode(1000);
            tableData.setMsg("查询成功");
            tableData.setCount(service.searchCount(searchData));
            tableData.setData(resultDataList);
        } else {
            tableData.setCode(2000);
            tableData.setMsg("无结果");
            tableData.setCount(0);
            tableData.setData(null);
        }
        return tableData;
    }

    @RequestMapping("/analysis")
    @ResponseBody
    public TableData analysisData(String type){
        TableData analysisData = new TableData();

        switch (type) {
            case "typeScore":
                List<Book_DataScore> resultDataList_typeScore = service.analysisTypeScore();
//                System.out.println(resultDataList_typeScore);
                if (resultDataList_typeScore != null){
                    analysisData.setCode(1000);
                    analysisData.setMsg("分析成功");
                    analysisData.setData(resultDataList_typeScore);
                }else {
                    analysisData.setCode(2000);
                    analysisData.setMsg("分析失败");
                    analysisData.setData(null);
                }
                break;
            case "hotAuthor":
                List<Book_DataHot> resultDataList_hotAuthor = service.analysisHotAuthor();
                System.out.println(resultDataList_hotAuthor);
                if (resultDataList_hotAuthor != null){
                    analysisData.setCode(1000);
                    analysisData.setMsg("分析成功");
                    analysisData.setData(resultDataList_hotAuthor);
                }else {
                    analysisData.setCode(2000);
                    analysisData.setMsg("分析失败");
                    analysisData.setData(null);
                }
                break;
            default:
                List<Book_Data> resultDataList = service.analysisBook(type);
                if (resultDataList != null){
                    analysisData.setCode(1000);
                    analysisData.setMsg("分析成功");
                    analysisData.setData(resultDataList);
                }else {
                    analysisData.setCode(2000);
                    analysisData.setMsg("分析失败");
                    analysisData.setData(null);
                }
        }

        return analysisData;
    }
}
