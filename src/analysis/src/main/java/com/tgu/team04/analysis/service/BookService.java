package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.Book;

import java.util.List;

public interface BookService {

//    List<Book> search(int page, int limit);

    List<Book> searchByOption(Book book, int page, int limit);

    int searchCount(Book book);
}
