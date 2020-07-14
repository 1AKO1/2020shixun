package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.Book_Data;
import com.tgu.team04.analysis.entity.Book_DataScore;
import com.tgu.team04.analysis.entity.Book_ResultData;
import com.tgu.team04.analysis.entity.Book_SearchData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {

    @Select("<script>" +
            "select ID, book_title as title, book_author as author, " +
            "book_press as press, book_type as type, book_score as score, book_votes as votes, "+
            "hotComm_content, hotComm_people, hotComm_score, hotComm_votes" +
            "   from tesla_book" +
            // 【条件】
            "<where>" +
            // 书名
            "   <if test='searchData.title != null and searchData.title.length > 0'>" +
            "       AND book_title like #{searchData.title}" +
            "   </if>" +
            // 作者
            "   <if test='searchData.author != null and searchData.author.length > 0'>" +
            "       AND book_author like #{searchData.author}" +
            "   </if>" +
            // 出版社
            "   <if test='searchData.press != null and searchData.press.length > 0'>" +
            "       AND book_author like #{searchData.press}" +
            "   </if>" +
            // 类型
            "   <if test='searchData.numOfType > 0'>" +
            "       AND book_type IN" +
            "       <foreach collection='searchData.type' item='type' index='index' open='(' close = ')' separator=', '>" +
            "           #{type}" +
            "       </foreach>" +
            "   </if>" +
            // ISBN号
            "   <if test='searchData.isbn != null and searchData.press.length > 0'>" +
            "       AND book_isbn like #{searchData.press}" +
            "   </if>" +
            // 评价人数
            "       AND book_votes >= #{searchData.minVotes}" +
            // 评分区间
            "       AND book_score BETWEEN #{searchData.minScore} AND #{searchData.MaxScore}" +
            "</where>" +
            // 页数处理
            "<if test='start != null and limit != null'>" +
            "   limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    List<Book_ResultData> selectByWhere(@Param("searchData") Book_SearchData searchData, @Param("start") Integer start, @Param("limit") Integer limit);

    @Select("<script>" +
            "select count(1)" +
            "   from tesla_book" +
            // 【条件】
            "<where>" +
            // 书名
            "   <if test='searchData.title != null and searchData.title.length > 0'>" +
            "       AND book_title like #{searchData.title}" +
            "   </if>" +
            // 作者
            "   <if test='searchData.author != null and searchData.author.length > 0'>" +
            "       AND book_author like #{searchData.author}" +
            "   </if>" +
            // 出版社
            "   <if test='searchData.press != null and searchData.press.length > 0'>" +
            "       AND book_author like #{searchData.press}" +
            "   </if>" +
            // 类型
            "   <if test='searchData.numOfType > 0'>" +
            "       AND book_type IN" +
            "       <foreach collection='searchData.type' item='type' index='index' open='(' close = ')' separator=', '>" +
            "           #{type}" +
            "       </foreach>" +
            "   </if>" +
            // ISBN号
            "   <if test='searchData.isbn != null and searchData.press.length > 0'>" +
            "       AND book_isbn like #{searchData.press}" +
            "   </if>" +
            // 评价人数
            "       AND book_votes >= #{searchData.minVotes}" +
            // 评分区间
            "       AND book_score BETWEEN #{searchData.minScore} AND #{searchData.MaxScore}" +

            "</where>" +
            "</script>")
    int countSelectByWhere(@Param("searchData")Book_SearchData searchData);

    @Select("select book_author as analysisData, count(1) as count " +
            "from tesla_book where book_author <> '无'" +
            "group by book_author order by count desc limit 0, 25")
    List<Book_Data> analysisAuthor();

    @Select("select DATE_FORMAT(book_pubDate,'%Y-%m') as analysisData ,count(1) as count \n" +
            "from tesla_book WHERE book_pubDate\n" +
            "group by DATE_FORMAT(book_pubDate,'%Y-%m') \n" +
            "order by DATE_FORMAT(book_pubDate,'%Y-%m')")
    List<Book_Data> analysisPubDate();

    @Select("SELECT AVG(book_score) AS score, AVG(book_fiveStar) AS fiveStar,\n" +
            "\t\tAVG(book_fourStar) AS fourStar, AVG(book_threeStar) AS threeStar,\n" +
            "\t\tAVG(book_twoStar) AS twoStar, AVG(book_oneStar) AS oneStar,\n" +
            "\t\tbook_type AS type FROM tesla_book GROUP BY book_type;")
    List<Book_DataScore> analysisTypeScore();

}
