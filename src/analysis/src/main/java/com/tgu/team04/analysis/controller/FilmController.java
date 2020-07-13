package com.tgu.team04.analysis.controller;

import com.tgu.team04.analysis.entity.*;
import com.tgu.team04.analysis.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/douban")
public class FilmController {
    @Autowired
    private FilmService filmservice;

    @RequestMapping("/filmlist")
    @ResponseBody
    public TableData filmList(Integer page, Integer limit){
        TableData data = new TableData();
        System.out.println("page: " + page + ", limit: " + limit);
        List<FilmComment> result = filmservice.search((int)page, (int)limit);
        if (result != null){
            data.setCode(1000);
            data.setMsg("查询成功");
            data.setData(result);
        }else {
            data.setCode(2000);
            data.setMsg("查询失败");
            data.setData(null);
        }
        return data;
    }

    @RequestMapping("/search")
    @ResponseBody
    public TableData search(String movieName, String movieDirector, String movieRole, int page, int limit){
        FilmComment filmComment = new FilmComment();
        filmComment.setMovieName(movieName);
        filmComment.setMovieDirector(movieDirector);
        filmComment.setMovieRole(movieRole);

        System.out.println(filmComment);
        System.out.println("page: " + page + ", limit: " + limit);
        TableData data = new TableData();
        List<FilmComment> result = filmservice.searchByOption(filmComment, page, limit);
        if (result != null && result.size() != 0){
            data.setCode(1000);
            data.setMsg("查询成功");
            data.setCount(filmservice.searchCount(filmComment));
            data.setData(result);
        }else {
            data.setCode(2000);
            data.setMsg("查询失败");
            data.setCount(0);
            data.setData(null);
        }
        return data;
    }

    @RequestMapping("/query")
    @ResponseBody
    public TableData query(String movieName){

        TableData data = new TableData();
        FilmSummeryData filmSummeryData = filmservice.movieSummery();
        List<FilmSummeryData>  result = new ArrayList<>();
        result.add(filmSummeryData);
        if (result != null && result.size() != 0){
            data.setCode(1000);
            data.setMsg("查询成功");
            data.setData(result);
        }else {
            data.setCode(2000);
            data.setMsg("查询失败");
            data.setCount(0);
            data.setData(null);
        }
        return data;
    }

    @RequestMapping("/totalnumber")
    @ResponseBody
    public int totalNumber(){
        int countTotal = filmservice.totalNumber();
        return countTotal;
    }

    @RequestMapping("/movielength")
    @ResponseBody
    public int movieLength(){
        int movielength = filmservice.movieLength();
        return movielength;
    }

    @RequestMapping("/peoplenumber")
    @ResponseBody
    public int peopleNumber(){
        int peoplenumber = filmservice.peopleNumber();
        return peoplenumber;
    }

    @RequestMapping("/maxscore")
    @ResponseBody
    public TableData maxScore(){
        TableData data = new TableData();
        System.out.println(data);
        List<Float> result = filmservice.maxScore();
        System.out.println(result);
        if (result != null){
            data.setCode(1000);
            data.setMsg("查询成功");
            data.setData(result);
        }else {
            data.setCode(2000);
            data.setMsg("查询失败");
            data.setData(null);
        }
        return data;
    }
    @RequestMapping("/simpleAnalysis")
    @ResponseBody
    public TableData simpleAnalysis(String type){
        TableData data = new TableData();
        List<FilmComment> filmComments = filmservice.simpleAnalysis(type);

        if ("score".equalsIgnoreCase(type)) {
            List<FilmSimpleData> result = new ArrayList<>();
            Map<String,Float> map=new HashMap<>();
            boolean flag=false;
            String judge=null;
            float num=0;
            for (FilmComment comment : filmComments) {
                String[] arr=comment.getMovieType().split("/");
                if(map.isEmpty()){
                    map.put(arr[0],comment.getScore());
                }
                for(String key:map.keySet()){
                    if(arr[0].equals(key)){
                        judge=key;
                        num=map.get(key);
                        flag=true;
                    }
                }
                if(flag){
                    map.put(judge,comment.getScore()+num);
                    flag=false;
                }else{
                    map.put(arr[0],comment.getScore());
                }
            }
            double count=0;
            for(String key:map.keySet()){
                FilmSimpleData filmSimpleData = new FilmSimpleData();
                filmSimpleData.setMovieType(key);
                filmSimpleData.setScore(map.get(key));
                count+=map.get(key);
                result.add(filmSimpleData);
            }
            System.out.println(count+"++++++++++++++++++++++++++++++++++++++");
            // 返回table data
            data = generateTableData(result);
        }
        if ("commentRise".equalsIgnoreCase(type)) {
            List<FilmCommentData> result = new ArrayList<FilmCommentData>();
            for (FilmComment comment : filmComments) {
                FilmCommentData filmCommentData = new FilmCommentData();
                filmCommentData.setPeopleCount(comment.getPeopleCount());
                float score = comment.getScore();
                filmCommentData.setScore(score);
                result.add(filmCommentData);
            }
            // 返回table data
            data = generateTableData(result);
        }
        if ("wordFrequency".equalsIgnoreCase(type)) {
            List<FilmAreaData> result = new ArrayList<FilmAreaData>();
            for (FilmComment comment : filmComments) {
                FilmAreaData filmAreaData = new FilmAreaData();
                filmAreaData.setArea(comment.getMovieCountry());
                filmAreaData.setCount(comment.getPeopleCount());
                result.add(filmAreaData);
            }
            // 返回table data
            data = generateTableData(result);
        }

        if ("fileCount".equalsIgnoreCase(type)) {
            List<FilmCountData> result = new ArrayList<FilmCountData>();
            for (FilmComment comment : filmComments) {
                String time = comment.getMovieStartTime();
                if (time == null) {
                    continue;
                }
                FilmCountData filmCountData = new FilmCountData();
                filmCountData.setFilmCount(comment.getPeopleCount());
                filmCountData.setTime(time);
                result.add(filmCountData);
            }
            // 返回table data
            data = generateTableData(result);
        }

        if ("timeScore".equalsIgnoreCase(type)) {
            List<FilmTimeScoreData> result = new ArrayList<FilmTimeScoreData>();
            for (FilmComment comment : filmComments) {
                float score = comment.getScore();
                FilmTimeScoreData filmTimeScoreData = new FilmTimeScoreData();
                filmTimeScoreData.setTimeLength(comment.getMovieLength());
                filmTimeScoreData.setScore(score);
                result.add(filmTimeScoreData);
            }
            // 返回table data
            data = generateTableData(result);
        }
        return data;
    }

    private <T> TableData generateTableData(List<T> result) {
        TableData data = new TableData();
        if (result != null){
            data.setCode(1000);
            data.setMsg("查询成功");
            data.setData(result);
        }else {
            data.setCode(2000);
            data.setMsg("查询失败");
            data.setData(null);
        }
        return data;
    }
}
