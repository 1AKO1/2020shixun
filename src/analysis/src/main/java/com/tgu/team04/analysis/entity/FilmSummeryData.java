package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class FilmSummeryData {

    private int movieLength;

    private int movieCount;

    private int movieCommentCount;

    private float movieMaxScore;

    private float movieMinScore;

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    public int getMovieCommentCount() {
        return movieCommentCount;
    }

    public void setMovieCommentCount(int movieCommentCount) {
        this.movieCommentCount = movieCommentCount;
    }

    public float getMovieMaxScore() {
        return movieMaxScore;
    }

    public void setMovieMaxScore(float movieMaxScore) {
        this.movieMaxScore = movieMaxScore;
    }

    public float getMovieMinScore() {
        return movieMinScore;
    }

    public void setMovieMinScore(float movieMinScore) {
        this.movieMinScore = movieMinScore;
    }
}
