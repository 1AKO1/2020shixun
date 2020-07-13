package com.tgu.team04.analysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Book_ResultData {
    // 两表格通用
    private Integer id;
    private String title;

    // 表格一数据：书籍信息
    private String author;
    private String press;
    private String type;
    private Float score;
    private Integer votes;

    // 表格二数据：热评信息
    private String hotCommContent;
    private String hotCommPeople;
    private String hotCommScore;
    private Integer hotCommVotes;
}
