package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.FilmMapper;
import com.tgu.team04.analysis.entity.AiraSimpleData;
import com.tgu.team04.analysis.entity.FilmComment;
import com.tgu.team04.analysis.entity.FilmSummeryData;
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
//        List<Float> maxmin = mapper.getMaxScore();
//        List<Float> list = new ArrayList<>();
//        int maxscore=mapper.getPeopleNumber();
//        int minscore=mapper.getTotalNumber();
//        list.add(1,maxscore);
//        list.add(2,minscore);
//        Float maxscore = mapper.
//        return maxmin;
        return  null;
    }

    @Override
    public List<FilmComment> searchByOption(FilmComment filmComment, int page, int limit) {
        if (filmComment.getMovieName() != null && !"".equals(filmComment.getMovieName().trim())){
            filmComment.setMovieName("%" + filmComment.getMovieName() + "%");
        }
        if(filmComment.getMovieDirector() != null && !"".equals(filmComment.getMovieDirector().trim())){
            filmComment.setMovieDirector("%" + filmComment.getMovieDirector() + "%");
        }
        if(filmComment.getMovieRole() != null && !"".equals(filmComment.getMovieRole().trim())){
            filmComment.setMovieRole("%" + filmComment.getMovieRole() + "%");
        }
        if (page > 0 && limit > 0){
            return mapper.selectByWhere(filmComment, (page - 1)*limit, limit);
        }
        return mapper.selectByWhere(filmComment, null, null);
    }

    @Override
    public int searchCount(FilmComment filmComment) {
        return mapper.countSelectByWhere(filmComment);
    }

    @Override
    public List<FilmComment> simpleAnalysis(String type) {
        List<FilmComment> comments = new ArrayList<>();
        switch (type) {
            case "score" :
                comments = mapper.simpleAnalysis();
                break;
            case "commentRise" :
                comments = mapper.commentAnalysis();
                break;
            case "wordFrequency" :
                comments = mapper.areaAnalysis();
                break;
            case "fileCount" :
                comments = mapper.filmCountAnalysis();
                break;
            case "timeScore" :
                comments = mapper.timeScoreAnalysis();
                break;

        }

        return comments;
    }

    @Override
    public FilmSummeryData movieSummery() {
        FilmSummeryData filmSummeryData  = new  FilmSummeryData();
        int movieCount = mapper.getTotalNumber();
        int movieLengh = mapper.getMovieLength();
        float maxScore = mapper.getMaxScore();
        float minScore = mapper.getMinScore();

        int commentCount = mapper.getCommentCount();
        filmSummeryData.setMovieMaxScore(maxScore);
        filmSummeryData.setMovieMinScore(minScore);
        filmSummeryData.setMovieCount(movieCount);
        filmSummeryData.setMovieLength(movieLengh);
        filmSummeryData.setMovieCommentCount(commentCount);
        return filmSummeryData;
    }
}
