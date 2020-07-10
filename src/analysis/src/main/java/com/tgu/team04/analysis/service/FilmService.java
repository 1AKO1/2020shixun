package com.tgu.team04.analysis.service;


import com.tgu.team04.analysis.entity.FilmComment;

import java.util.List;


public interface FilmService {
    List<FilmComment> search(int page, int limit);

    int totalNumber();

    int movieLength();

    int peopleNumber();

}
