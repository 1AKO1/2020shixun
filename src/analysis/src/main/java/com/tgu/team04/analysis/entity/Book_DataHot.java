package com.tgu.team04.analysis.entity;

import lombok.Data;

import java.util.List;

@Data
public class Book_DataHot {
    private Integer hot;
    private Float votes;
    private Float score;

    private String author;
}
