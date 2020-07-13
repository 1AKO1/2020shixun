package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class Book_SearchData {

    private String title;   // 书名
    private String author;  // 作者
    private String press;   // 出版社
    private int numOfType;  // 类别个数
    private String[] type;  // 类别
    private String isbn;    // ISBN号
    private int minVotes;   // 最小评价数
    private float minScore; // 最小豆瓣评分
    private float MaxScore; // 最大豆瓣评分

    private int page;       // 页码
    private int limit;      // 每页数量
}
