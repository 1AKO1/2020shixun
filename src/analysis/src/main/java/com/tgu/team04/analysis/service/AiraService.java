package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.AiraComment;
import com.tgu.team04.analysis.entity.AiraSimpleData;

import java.util.List;

public interface AiraService {
    List<AiraComment> search(int page, int limit);

    int getScore(String comment);

    List<AiraComment> searchByOption(AiraComment comment, int page, int limit);

    int searchCount(AiraComment comment);

    List<AiraSimpleData> simpleAnalysis(String type);

    List<AiraSimpleData> advanceAnalysis();
}
