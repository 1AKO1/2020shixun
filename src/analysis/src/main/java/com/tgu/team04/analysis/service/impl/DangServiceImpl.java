package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.controller.DangdangController;
import com.tgu.team04.analysis.dao.DangMapper;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.dangdangBook;
import com.tgu.team04.analysis.entity.dangdangData;
import com.tgu.team04.analysis.service.DangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DangServiceImpl implements DangService {

    @Autowired
    private DangMapper mapper;


    @Override
    public List<dangdangBook> searchByOptions(dangdangBook Book, int page, int limit) {
        if (Book.getName() != null && !"".equals(Book.getName().trim())){
            Book.setName("%" + Book.getName() + "%");
        }
        if(Book.getAuthor() != null && !"".equals(Book.getAuthor().trim())){
            Book.setAuthor("%" + Book.getAuthor() + "%");
        }
        float min = DangdangController.min;
        float max = DangdangController.max;
        System.out.println(min);
        if (page > 0 && limit > 0){
            return mapper.selectByWhere(Book, (page - 1)*limit, limit,min,max);
        }
        return mapper.selectByWhere(Book, null, null,min,max);
    }


    @Override
    public int searchCount(dangdangBook Book) {
        return mapper.countSelectByWhere(Book);
    }

    @Override
    public List<dangdangData> dangAnalysis(String type) {
        if (type == null) return null;

        switch (type){
            case "classes":
                return mapper.classes();
            case "prosstime":
                return mapper.prosstime();
            case "AvgClass":
                return mapper.avgclass();
            case "commentpr":
                return mapper.commentpr();
            case "yearBook":
                return mapper.yearBook();

        }

        return null;
    }
}
