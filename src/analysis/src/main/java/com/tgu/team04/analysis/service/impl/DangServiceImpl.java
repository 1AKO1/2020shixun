package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.DangMapper;
import com.tgu.team04.analysis.entity.dangdangBook;
import com.tgu.team04.analysis.service.DangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DangServiceImpl implements DangService {

//    @Autowired
//    private DangMapper mapper;


    @Override
    public List<dangdangBook> searchByOptions(dangdangBook Book, int page, int limit) {
        return null;
    }

    @Override
    public int searchCount(dangdangBook Book) {
        return 0;
    }
}
