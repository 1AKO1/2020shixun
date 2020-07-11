package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.Music;
import com.tgu.team04.analysis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MusicMapper {

    @Select("<script>" +
            "select music_name as name, music_singer as singer, music_time as time, music_rating as rate,music_tags as tag,music_kind as kind,music_recommend as recommend" +
            "  from spt_music" +
            "<where>" +
            "   <if test='name !=null and name.length > 0'>" +
            "       and music_name like #{name}" +
            "   </if>"+
            "   <if test='singer !=null and singer.length > 0'>" +
            "       and music_singer like #{singer}" +
            "   </if>"+
            "   <if test='time !=null and time.length > 0'>" +
            "       and music_time like #{time}" +
            "   </if>"+
            "   <if test='tag !=null and tag.length > 0'>" +
            "       and music_tags like #{tag}" +
            "   </if>"+
            "   <if test='kind !=null and kind.length > 0'>" +
            "       and music_kind like #{kind}" +
            "   </if>"+
            "</where>" +
//            "<if test='start != null and limit != null'>" +
//            "   limit #{start}, #{limit}" +
//            "</if>"+
            "</script>")
    public List<Music> selectByWhere(@Param("name") String name, @Param("singer") String singer, @Param("time") String time, @Param("tag") String tag, @Param("kind") String kind, @Param("start") Integer start, @Param("limit") Integer limit);

    @Select("<script>" +
            "select count(1)" +
            "from spt_music" +
            "<where>" +
            "   <if test='name !=null and name.length > 0'>" +
            "       and music_name like #{name}" +
            "   </if>"+
            "   <if test='singer !=null and singer.length > 0'>" +
            "       and music_singer like #{singer}" +
            "   </if>"+
            "   <if test='time !=null and time.length > 0'>" +
            "       and music_time like #{time}" +
            "   </if>"+
            "   <if test='tag !=null and tag.length > 0'> " +
            "       and music_tags like #{tag}" +
            "   </if>"+
            "   <if test='kind !=null and kind.length > 0 '>" +
            "       and music_kind like #{kind}" +
            "   </if>"+
            "</where>" +
            "</script>")
    public int countSeletcByWhere(String name, String singer, String time, String tag, String kind);
}
