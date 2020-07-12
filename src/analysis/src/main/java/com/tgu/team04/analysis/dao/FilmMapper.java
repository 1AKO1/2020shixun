package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.FilmComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FilmMapper {
    //    @Select("select movie_name, score, movie_type, movie_director, movie_script, movie_role, movie_country, movie_language, movie_start_time, movie_length," +
//            "from bdcy_douban_movie " +
//            "order by score " +
//            "limit #{start}, #{limit};")
    @Select("select * " +
            "from bdcy_douban_movie " +
            "order by score " +
            "limit #{start}, #{limit};")
    List<FilmComment> selectCommentList(@Param("start") int start, @Param("limit") int limit);

    @Select("select count(0)" +
            "from bdcy_douban_movie ")
    int getTotalNumber();

    @Select("select AVG(DISTINCT movie_length) " +
            "from bdcy_douban_movie " )
    int getMovieLength();

    @Select("select sum(people_count)" +
            "from bdcy_douban_movie")
    int getPeopleNumber();

    @Select("select sum(review_count)" +
            "from bdcy_douban_movie")
    int getCommentCount();

    @Select("select max(score)" +
            "from bdcy_douban_movie")
    float getMaxScore();

    @Select("select min(score)" +
            "from bdcy_douban_movie")
    float getMinScore();

    @Select("select max(score),min(score)" +
            "from bdcy_douban_movie")
    List<Float> getScore();

    @Select("select count(movie_type)" +
            "from bdcy_douban_movie" +
            "where movie_type=#{type}")
    List<FilmComment> getTypeSort(@Param("type") List<String> type);

    @Select("<script>" +
            "select a.id,a.movie_name,a.score, a.people_count, a.five_star, a.four_star,a.three_star," +
            "a.two_star,a.one_star,a.short_review_count,a.review_count,a.movie_type,a.movie_director,a.movie_script," +
            "a.movie_role,a.movie_country,a.movie_language,a.movie_start_time,a.movie_length,a.movie_url " +
            "from bdcy_douban_movie a\n" +
            "<where>" +
            "   <if test='comment.movieName != null and comment.movieName.length > 0'>" +
            "       AND a.movie_name like #{comment.movieName}" +
            "   </if>" +
            "   <if test='comment.movieDirector != null and comment.movieDirector.length > 0'>" +
            "       AND a.movie_director like #{comment.movieDirector}" +
            "   </if>" +
            "   <if test='comment.movieRole != null and comment.movieRole.length > 0'>" +
            "       AND a.movie_role like  #{comment.movieRole}" +
            "   </if>" +
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "   limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    List<FilmComment> selectByWhere( @Param("comment")FilmComment comment,  @Param("start")Integer start,  @Param("limit")Integer limit);


    @Select("<script>" +
            "select count(1)" +
            "   from bdcy_douban_movie a" +
            "<where>" +
            "   <if test='comment.movieName != null and comment.movieName.length > 0'>" +
            "       AND a.movie_name like #{comment.movieName}" +
            "   </if>" +
            "   <if test='comment.movieDirector != null and comment.movieDirector.length > 0'>" +
            "       AND a.movie_director like #{comment.movieDirector}" +
            "   </if>" +
            "   <if test='comment.movieRole != null and comment.movieRole.length > 0'>" +
            "       AND a.movie_role like  #{comment.movieRole}" +
            "   </if>" +

            "</where>" +

            "</script>")
    int countSelectByWhere(@Param("comment")FilmComment filmComment);

    @Select("select movie_type,avg(score) score " +
            "from bdcy_douban_movie " +
            "group by movie_type " +
            "ORDER BY score DESC LIMIT 20;")
    List<FilmComment> simpleAnalysis();

    @Select("SELECT  score, sum(people_count) people_count  FROM `bdcy_douban_movie`\n" +
            "group by score ORDER BY score ASC;")
    List<FilmComment> commentAnalysis();
    @Select("SELECT movie_country, count(movie_country) people_count FROM `bdcy_douban_movie`\n" +
            "GROUP BY movie_country ORDER BY people_count DESC limit 20;")
    List<FilmComment> areaAnalysis();

    @Select("SELECT year(movie_start_time) movie_start_time, count(id) people_count FROM bdcy_douban_movie\n" +
            " GROUP BY year(movie_start_time)\n" +
            "ORDER BY movie_start_time;")
    List<FilmComment> filmCountAnalysis();

    @Select("SELECT  max(score) score ,movie_length  FROM `bdcy_douban_movie`\n" +
            "group by score\n" +
            "ORDER BY score")
    List<FilmComment> timeScoreAnalysis();

}
//id, movie_name, score, people_count, five_star, four_star, three_star, two_star, one_star, short_review_count, review_count, movie_type, movie_director, movie_script, movie_role, movie_country, movie_language, movie_start_time, movie_length, movie_url
