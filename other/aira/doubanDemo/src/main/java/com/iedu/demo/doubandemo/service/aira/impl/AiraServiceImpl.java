package com.iedu.demo.doubandemo.service.aira.impl;

import com.iedu.demo.doubandemo.dao.AiraMapper;
import com.iedu.demo.doubandemo.entity.aira.Comment;
import com.iedu.demo.doubandemo.service.aira.AiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiraServiceImpl implements AiraService {

    @Autowired
    private AiraMapper mapper;

    @Override
    public List<Comment> search(int page, int limit) {
        System.out.println("page: " + page + ", limit: " + limit);
        if(page > 0 && limit > 0){
            System.out.println("查询结果" + mapper.selectCommentList((page - 1)*limit, limit));
            return mapper.selectCommentList((page - 1)*limit, limit);
        }
        return null;
    }
}
