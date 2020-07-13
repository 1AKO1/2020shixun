package com.tgu.team04.analysis.entity;

public class Book_ResultData {
    // 两表格通用
    private int id;
    private String title;

    // 表格一：书籍信息
    private String author;
    private String press;
    private String type;
    private float score;
    private int votes;

    // 表格二：热评信息
    private String hotCommContent;
    private String hotCommPeople;
    private String hotCommScore;
    private int hotCommVotes;
}
