package com.tgu.team04.analysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Book_SearchData {

    private String title;       // 书名
    private String author;      // 作者
    private String press;       // 出版社
    private Integer numOfType;  // 类别个数
    private String[] type;      // 类别
    private String isbn;        // ISBN号
    private Integer minVotes;   // 最小评价数
    private Float minScore;     // 最小豆瓣评分
    private Float MaxScore;     // 最大豆瓣评分

    private Integer page;           // 页码
    private Integer limit;          // 每页数量

    // 通过前端发送type0字符串构建type字符串数组，方便后续查询
    public void setType(String type0) {
        this.type = type0.split(" ");
    }
}
