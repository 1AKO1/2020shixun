package com.iedu.demo.doubandemo.entity.aira;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private int id;
    private String avatar;
    private String uname;
    private String content;
    private int likes;
    private String time;
    private String progress;
    private int score;
}
