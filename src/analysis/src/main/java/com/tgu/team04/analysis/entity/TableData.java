package com.tgu.team04.analysis.entity;

import lombok.Data;

import java.util.List;

@Data
public class TableData {

    private int code;
    private String msg;
    private List<? extends Object> data;
}
