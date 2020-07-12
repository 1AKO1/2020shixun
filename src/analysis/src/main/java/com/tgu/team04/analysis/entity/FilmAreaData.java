package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class FilmAreaData {

    private String area;

    private float count;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }
}
