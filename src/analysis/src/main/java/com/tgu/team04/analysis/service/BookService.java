package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.Book;
import com.tgu.team04.analysis.entity.Book_Data;
import com.tgu.team04.analysis.entity.Book_ResultData;
import com.tgu.team04.analysis.entity.Book_SearchData;

import java.util.List;

public interface BookService {

//    List<Book> search(int page, int limit);

    List<Book_ResultData> searchByOptions(Book_SearchData searchData);

    int searchCount(Book_SearchData searchData);

    List<Book_Data> analysisBook(String type);


}
