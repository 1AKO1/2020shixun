package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.AiraComment;
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
}
