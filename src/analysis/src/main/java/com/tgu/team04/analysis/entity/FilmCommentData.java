package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class FilmCommentData {

    private int peopleCount;

    private float score;

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
