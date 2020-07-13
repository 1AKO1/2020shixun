package com.tgu.team04.analysis.service;


import com.tgu.team04.analysis.entity.AiraSimpleData;
import com.tgu.team04.analysis.entity.FilmComment;
import com.tgu.team04.analysis.entity.FilmSummeryData;

import java.util.List;


public interface FilmService {
    List<FilmComment> search(int page, int limit);

    int totalNumber();

    int movieLength();

    int peopleNumber();

    List<Float> maxScore();

    List<FilmComment> searchByOption(FilmComment filmComment, int page, int limit);


    int searchCount(FilmComment filmComment);

    List<FilmComment> simpleAnalysis(String type);

    FilmSummeryData movieSummery();

}
