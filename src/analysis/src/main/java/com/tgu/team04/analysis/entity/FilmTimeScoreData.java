package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class FilmTimeScoreData {

    private int timeLength;

    private float score;

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
