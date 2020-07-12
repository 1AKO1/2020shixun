package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.FilmMapper;
import com.tgu.team04.analysis.entity.FilmComment;
import com.tgu.team04.analysis.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmMapper mapper;

    @Override
    public List<FilmComment> search(int page, int limit) {
        System.out.println("page: " + page + ", limit: " + limit);
        if(page > 0 && limit > 0){
            System.out.println("查询结果" + mapper.selectCommentList((page - 1)*limit, limit));
            return mapper.selectCommentList((page - 1)*limit, limit);
        }
        return null;
    }

    @Override
    public int totalNumber() {
        int countTotal=mapper.getTotalNumber();

        return countTotal;
    }

    @Override
    public int movieLength() {
        int movielength=mapper.getMovieLength();

        return movielength;
    }

    @Override
    public int peopleNumber() {
        int peoplenumber=mapper.getPeopleNumber();

        return peoplenumber;
    }

    @Override
    public List<Float> maxScore() {
        List<Float> maxmin = mapper.getMaxScore();
//        List<Float> list = new ArrayList<>();
//        int maxscore=mapper.getPeopleNumber();
//        int minscore=mapper.getTotalNumber();
//        list.add(1,maxscore);
//        list.add(2,minscore);
//        Float maxscore = mapper.
        return maxmin;
    }
}
