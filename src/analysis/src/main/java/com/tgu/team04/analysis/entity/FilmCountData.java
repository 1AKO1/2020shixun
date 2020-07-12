package com.tgu.team04.analysis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FilmCountData {

    private int filmCount;

    private String time;

    public int getFilmCount() {
        return filmCount;
    }

    public void setFilmCount(int filmCount) {
        this.filmCount = filmCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
