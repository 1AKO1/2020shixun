package com.tgu.team04.analysis.service.impl;


import com.tgu.team04.analysis.dao.AiraMapper;
import com.tgu.team04.analysis.entity.AiraComment;
import com.tgu.team04.analysis.entity.AiraSimpleData;
import com.tgu.team04.analysis.service.AiraService;
import com.tgu.team04.analysis.tools.AiraTools;
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

    @Override
    public int getScore(String comment) {
        AiraTools airaTools = new AiraTools();
        int score = 0;
        try {
            score = airaTools.MyClassifier(comment);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return score;
    }

    @Override
    public List<AiraComment> searchByOption(AiraComment comment, int page, int limit) {
        if (comment.getUname() != null && !"".equals(comment.getUname().trim())){
            comment.setUname("%" + comment.getUname() + "%");
        }
        if(comment.getContent() != null && !"".equals(comment.getContent().trim())){
            comment.setContent("%" + comment.getContent() + "%");
        }
        if (page > 0 && limit > 0){
            return mapper.selectByWhere(comment, (page - 1)*limit, limit);
        }
        return mapper.selectByWhere(comment, null, null);
    }

    @Override
    public int searchCount(AiraComment comment) {
        return mapper.countSelectByWhere(comment);
    }

    @Override
    public List<AiraSimpleData> simpleAnalysis(String type) {
        if (type == null)  return null;
        switch (type){
            case "vipStatus":
                return mapper.simpleAnalysisVipStatus();
            case "progress":
                return mapper.simpleAnalysisProgress();
            case "score":
                return mapper.simpleAnalysisScore();
        }

        return null;
    }


}
