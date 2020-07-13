package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.Book_ResultData;
import com.tgu.team04.analysis.entity.Book_SearchData;
import com.tgu.team04.analysis.entity.TableData;
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
    public TableData searchBook(String title, String author, String press,
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

}
