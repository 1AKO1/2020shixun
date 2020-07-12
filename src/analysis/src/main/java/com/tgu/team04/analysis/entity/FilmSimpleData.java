package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class FilmSimpleData {

    private String movieType;

    private float score;

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "FilmSimpleData{" +
                "movieType='" + movieType + '\'' +
                ", score=" + score +
                '}';
    }
}
