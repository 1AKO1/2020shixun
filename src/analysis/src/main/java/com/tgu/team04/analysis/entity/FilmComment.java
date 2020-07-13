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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public float getFiveStar() {
        return fiveStar;
    }

    public void setFiveStar(float fiveStar) {
        this.fiveStar = fiveStar;
    }

    public float getFourStar() {
        return fourStar;
    }

    public void setFourStar(float fourStar) {
        this.fourStar = fourStar;
    }

    public float getThreeStar() {
        return threeStar;
    }

    public void setThreeStar(float threeStar) {
        this.threeStar = threeStar;
    }

    public float getTwoStar() {
        return twoStar;
    }

    public void setTwoStar(float twoStar) {
        this.twoStar = twoStar;
    }

    public float getOneStar() {
        return oneStar;
    }

    public void setOneStar(float oneStar) {
        this.oneStar = oneStar;
    }

    public int getShortReviewCount() {
        return shortReviewCount;
    }

    public void setShortReviewCount(int shortReviewCount) {
        this.shortReviewCount = shortReviewCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieScript() {
        return movieScript;
    }

    public void setMovieScript(String movieScript) {
        this.movieScript = movieScript;
    }

    public String getMovieRole() {
        return movieRole;
    }

    public void setMovieRole(String movieRole) {
        this.movieRole = movieRole;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieStartTime() {
        return movieStartTime;
    }

    public void setMovieStartTime(String movieStartTime) {
        this.movieStartTime = movieStartTime;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }
}
