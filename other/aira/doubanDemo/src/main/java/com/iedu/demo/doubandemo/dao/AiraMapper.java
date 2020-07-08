package com.iedu.demo.doubandemo.dao;
import com.iedu.demo.doubandemo.entity.User;
import com.iedu.demo.doubandemo.entity.aira.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AiraMapper {

    @Select("select id, avatar, uname, content, likes, ctime, progress, score " +
            "from aira_kaguya_sama " +
            "limit #{start}, #{limit} ;")
    List<Comment> selectCommentList(@Param("start") int start, @Param("limit") int limit);
}
