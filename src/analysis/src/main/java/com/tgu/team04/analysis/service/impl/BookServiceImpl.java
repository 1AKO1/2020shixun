package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.entity.Book;
import com.tgu.team04.analysis.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public List<Book> searchByOption(Book book, int page, int limit) {
        return null;
    }

    @Override
    public int searchCount(Book book) {
        return 0;
    }
}
