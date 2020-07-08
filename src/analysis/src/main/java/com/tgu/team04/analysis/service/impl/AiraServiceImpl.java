package com.tgu.team04.analysis.service.impl;


import com.tgu.team04.analysis.dao.AiraMapper;
import com.tgu.team04.analysis.entity.AiraComment;
import com.tgu.team04.analysis.service.AiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiraServiceImpl implements AiraService {

    @Autowired
    private AiraMapper mapper;

    @Override
    public List<AiraComment> search(int page, int limit) {
        System.out.println("page: " + page + ", limit: " + limit);
        if(page > 0 && limit > 0){
            System.out.println("查询结果" + mapper.selectCommentList((page - 1)*limit, limit));
            return mapper.selectCommentList((page - 1)*limit, limit);
        }
        return null;
    }
}
