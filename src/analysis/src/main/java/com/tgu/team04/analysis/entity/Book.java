package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class Book {

    private Integer id;     // 数据库ID

    // 基础信息
    private String title;   // 书名
    private String author;  // 作者
    private String type;    // 类别
    private String press;   // 出版社
    private String pubData; // 出版时间
    private String tags;    // 标签
    private String price;   // 参考定价
    private String isbn;    // ISBN号
    private String report;  // 内容简介
    private String dbid;    // 豆瓣ID
    private Integer versions;   // 书的总版本数

    // 评价信息
    private Float score;    // 豆瓣评分
    private Integer votes;      // 评价人次
    private Integer shortComms; // 短评数量
    private Integer reviews;    // 书评数量
    private Float fiveStar; // 五星占比
    private Float fourStar; // 四星占比
    private Float threeStar;// 三星占比
    private Float twoStar;  // 二星占比
    private Float oneStar;  // 一星占比
    private Integer readings;   // 在读人数
    private Integer reads;      // 已读人数
    private Integer toReads;    // 想读人数

    private String url;     // 图书URL
    private String picUrl;  // 图书封面图片URL

    private String hotCommContent;  // 热门短评内容
    private String hotCommPeople;   // 短评用户
    private String hotCommScore;    // 短评评分
    private Integer hotCommVotes;       // 短评点赞数

}
