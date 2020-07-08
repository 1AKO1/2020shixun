package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class FilmComment {
    private int id;
    private String movieName;
    private float score;
    private int peopleCount;
    private float fiveStar;
    private float fourStar;
    private float threeStar;
    private float twoStar;
    private float oneStar;
    private int shortReviewCount;
    private int reviewCount;
    private String movieType;
    private String movieDirector;
    private String movieScript;
    private String movieRole;
    private String movieCountry;
    private String movieLanguage;
    private String movieStartTime;
    private int movieLength;
    private String movieUrl;
}
