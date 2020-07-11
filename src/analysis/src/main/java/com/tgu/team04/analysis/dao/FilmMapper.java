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
    @Select("select *" +
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

    @Select("select max(score)" +
            "from bdcy_douban_movie")
    float getMaxScore();

    @Select("select min(score)" +
            "from bdcy_douban_movie")
    float getMinScore();
}
//id, movie_name, score, people_count, five_star, four_star, three_star, two_star, one_star, short_review_count, review_count, movie_type, movie_director, movie_script, movie_role, movie_country, movie_language, movie_start_time, movie_length, movie_url
