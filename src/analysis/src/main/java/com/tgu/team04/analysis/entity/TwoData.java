package com.tgu.team04.analysis.entity;

import lombok.Data;

import java.util.List;
@Data
public class TwoData {
    private int code;
    private String msg;
    private List<? extends Object> data1;
    private List<? extends Object> data2;
}
