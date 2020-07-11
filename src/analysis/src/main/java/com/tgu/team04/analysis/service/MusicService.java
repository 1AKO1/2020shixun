package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.Music;
import com.tgu.team04.analysis.entity.User;

import java.util.List;

public interface MusicService {


    List<Music> search(String name,String singer,String time , String tag,String kind, int page, int limit);

    int searchCount(String name, String singer, String time, String tag, String kind);
}
