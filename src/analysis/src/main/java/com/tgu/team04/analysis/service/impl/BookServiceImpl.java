package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.BookMapper;
import com.tgu.team04.analysis.entity.Book;
import com.tgu.team04.analysis.entity.Book_Data;
import com.tgu.team04.analysis.entity.Book_ResultData;
import com.tgu.team04.analysis.entity.Book_SearchData;
import com.tgu.team04.analysis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper mapper;

    @Override
    public List<Book_ResultData> searchByOptions(Book_SearchData searchData) {
        // 对四个String类型的查询属性做模糊查询预处理
        if (searchData.getTitle() != null && !"".equals(searchData.getTitle().trim())) {
            searchData.setTitle("%" + searchData.getTitle() + "%");
        }
        if (searchData.getAuthor() != null && !"".equals(searchData.getAuthor().trim())) {
            searchData.setAuthor("%" + searchData.getAuthor() + "%");
        }
        if (searchData.getPress() != null && !"".equals(searchData.getPress().trim())) {
            searchData.setPress("%" + searchData.getPress() + "%");
        }
        if (searchData.getIsbn() != null && !"".equals(searchData.getIsbn().trim())) {
            searchData.setIsbn("%" + searchData.getIsbn() + "%");
        }
        // 处理查询条数 为分页准备
        if (searchData.getPage() > 0 && searchData.getLimit() > 0){
            return mapper.selectByWhere(searchData, (searchData.getPage() - 1)*searchData.getLimit(), searchData.getLimit());
        }
        return mapper.selectByWhere(searchData, null, null);
    }

    @Override
    public int searchCount(Book_SearchData searchData) {
        return mapper.countSelectByWhere(searchData);
    }

    @Override
    public List<Book_Data> analysisBook(String type) {
        if (type == null) return null;

        switch (type) {
            case "author":
                return mapper.analysisAuthor();
//            case "prosstime":
//                return mapper.prosstime();
//            case "AvgClass":
//                return mapper.avgclass();
//            case "commentpr":
//                return mapper.commentpr();
//            case "yearBook":
//                return mapper.yearBook();

        }

        return null;
    }
}
