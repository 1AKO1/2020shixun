package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.Book;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/doubanBook")
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping("/search")
    @ResponseBody
    public TableData searchBook(String title, String author, String type,
                                String press, float score, int page, int limit) {
        Book book = new Book();
        book.setTitle(title.length() == 0 ? null : title);
        book.setAuthor(author.length() == 0 ? null : author);
        book.setPress(press);
        book.setScore(score);

        TableData tableList = new TableData();
//        List<Book> result =

        return tableList;
    }

}
