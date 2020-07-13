package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.dangdangBook;
import com.tgu.team04.analysis.entity.dangdangData;

import java.util.List;

public interface DangService {

    List<dangdangBook> searchByOptions(dangdangBook Book, int page, int limit);

    int searchCount(dangdangBook Book);

    List<dangdangData> dangAnalysis(String type);
}
