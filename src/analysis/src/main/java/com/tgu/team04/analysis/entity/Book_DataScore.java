package com.tgu.team04.analysis.entity;

import lombok.Data;

import java.util.List;

@Data
public class Book_DataScore {
    private List<Float> analysisData;

    private String type;

//    public void setAnalysisData(Book_DataScore0 data0) {
//        this.analysisData.add(data0.getScore());
//        this.analysisData.add(data0.getFiveStar());
//        this.analysisData.add(data0.getFourStar());
//        this.analysisData.add(data0.getThreeStar());
//        this.analysisData.add(data0.getTwoStar());
//        this.analysisData.add(data0.getOneStar());
//
//        this.type = data0.getType();
//    }
}
