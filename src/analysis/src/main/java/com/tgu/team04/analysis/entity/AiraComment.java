package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class AiraComment {
    private int id;
    private String avatar;
    private String uname;
    private String content;
    private int likes;
    private String ctime;
    private String progress;
    private int score;
    private int vipStatus;
}
