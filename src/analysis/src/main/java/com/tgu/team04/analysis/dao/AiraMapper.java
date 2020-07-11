package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.AiraComment;
import com.tgu.team04.analysis.entity.AiraSimpleData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AiraMapper {

    @Select("select id, avatar, uname, content, likes, ctime, progress, score " +
            "from aira_kaguya_sama " +
            "limit #{start}, #{limit} ;")
    List<AiraComment> selectCommentList(@Param("start") int start, @Param("limit") int limit);


    @Select("<script>" +
            "select id, uname, content, ctime, progress, vipStatus" +
            "   from aira_kaguya_sama" +
            "<where>" +
            "   <if test='comment.uname != null and comment.uname.length > 0'>" +
            "       AND uname like #{comment.uname}" +
            "   </if>" +
            "   <if test='comment.content != null and comment.content.length > 0'>" +
            "       AND content like #{comment.content}" +
            "   </if>" +
            "   <if test='comment.vipStatus != null and comment.vipStatus != -1'>" +
            "       AND vipStatus = #{comment.vipStatus}" +
            "   </if>" +
            "   <if test='comment.progress != null and comment.progress != \"all\" '>" +
            "       AND progress = #{comment.progress}" +
            "   </if>" +
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "   limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    List<AiraComment> selectByWhere(@Param("comment") AiraComment comment, @Param("start") Integer start, @Param("limit") Integer limit);

    @Select("<script>" +
            "select count(1)" +
            "   from aira_kaguya_sama" +
            "<where>" +
            "   <if test='comment.uname != null and comment.uname.length > 0'>" +
            "       AND uname like #{comment.uname}" +
            "   </if>" +
            "   <if test='comment.content != null and comment.content.length > 0'>" +
            "       AND content like #{comment.content}" +
            "   </if>" +
            "   <if test='comment.vipStatus != null and comment.vipStatus != -1'>" +
            "       AND vipStatus = #{comment.vipStatus}" +
            "   </if>" +
            "   <if test='comment.progress != null and comment.progress != \"all\" '>" +
            "       AND progress = #{comment.progress}" +
            "   </if>" +
            "</where>" +
            "</script>")
    int countSelectByWhere(@Param("comment") AiraComment comment);


    @Select("SELECT vipStatus as type, COUNT(1) as count FROM aira_kaguya_sama GROUP BY vipStatus;")
    List<AiraSimpleData> simpleAnalysisVipStatus();

    @Select("SELECT progress as type, COUNT(1) as count FROM aira_kaguya_sama GROUP BY progress;")
    List<AiraSimpleData> simpleAnalysisProgress();

    @Select("SELECT score as type, COUNT(1) as count FROM aira_kaguya_sama GROUP BY score;")
    List<AiraSimpleData> simpleAnalysisScore();

    @Select("SELECT ctime as type, COUNT(1) as count FROM commentRise GROUP BY ctime;")
    List<AiraSimpleData> simpleAnalysisCommentRise();

    @Select("SELECT word AS `type`, frequency AS `count` FROM word_frequency LIMIT 20;")
    List<AiraSimpleData> simpleAnalysisWordFrequency();

}
