package com.tgu.team04.analysis.dao;

import com.tgu.team04.analysis.entity.dangdangBook;
import com.tgu.team04.analysis.entity.dangdangData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface DangMapper {
    @Select("<script>" +
            "select id, name, author, ptimes, samlllei, pn ,tuijian ,ps" +
            "   from zhihan_dang" +
            "<where>" +
            "   <if test='Book.name != null and Book.name.length > 0'>" +
            "       AND name like #{Book.name}" +
            "   </if>" +
            "   <if test='Book.author != null and Book.author.length > 0'>" +
            "       AND author like #{Book.author}" +
            "   </if>" +
            "   <if test='Book.samlllei != null '>" +
            "       AND samlllei = #{Book.samlllei}" +
            "   </if>" +
            "   <if test='Book.ps >= 0 '>"+
            "       AND ps &lt; #{Book.ps}"+
            "   </if>"+
            "   <if test='Book.pn >= 0 '>" +
            "   AND pn &gt; #{min} and pn &lt; #{max}"+
            "   </if>"+
            "</where>" +
            "<if test='start != null and limit != null'>" +
            "   limit #{start}, #{limit}" +
            "</if>" +
            "</script>")
    List<dangdangBook> selectByWhere(@Param("Book") dangdangBook Book, @Param("start") Integer start, @Param("limit") Integer limit,@Param("min") float min,@Param("max")float max);


    @Select("<script>" +
            "select count(1)" +
            "   from zhihan_dang" +
            "<where>" +
            "   <if test='Book.name != null and Book.name.length > 0'>" +
            "       AND name like #{Book.name}" +
            "   </if>" +
            "   <if test='Book.author != null and Book.author.length > 0'>" +
            "       AND author like #{Book.author}" +
            "   </if>" +
            "   <if test=' Book.samlllei != null '>" +
            "       AND samlllei = #{Book.samlllei}" +
            "   </if>" +
            "   <if test='Book.ps >= 0 '>"+
            "       AND ps &lt; #{Book.ps}"+
            "   </if>"+
//            "   <if test='Book.pn &gt; Book.min and Book.pn &lt; Book.max'>" +
//            "       AND pn = #{Book.pn}" +
//            "   </if>" +
            "</where>" +
            "</script>")
    int countSelectByWhere(@Param("Book") dangdangBook Book);

    @Select("select biglei as type, count(*) as count from zhihan_dang  group by biglei order by count desc limit 0,40")
    List<dangdangData> classes();

}
