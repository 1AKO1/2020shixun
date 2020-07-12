package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.MusicMapper;
import com.tgu.team04.analysis.entity.Music;
import com.tgu.team04.analysis.entity.MusicData;
import com.tgu.team04.analysis.entity.TwoData;
import com.tgu.team04.analysis.entity.User;
import com.tgu.team04.analysis.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper mapper;

    @Override
    public List<Music> search(String name, String singer, String time , String tag, String kind, int page, int limit) {

        if (name!=null && !"".equals(name.trim()))
            name = "%"+name+"%";
        else name=null;

        if (singer!=null && !"".equals(singer.trim()))
            singer = "%"+singer+"%";
        else singer=null;

        if (time!=null && !"".equals(time.trim()))
            time = "%"+time+"%";
        else time=null;

        if (tag!=null && !"".equals(tag.trim()))
            tag = "%"+tag+"%";
        else tag=null;

        if (kind!=null && !"".equals(kind.trim())){
            switch(Integer.valueOf(kind)){
                case -1 :
                    kind = null;
                    break;
                case 0 :
                    kind = "%古典%";
                    break;
                case 1 :
                    kind = "%摇滚%";
                    break;
                case 2 :
                    kind = "%放克/灵歌/R&amp;B%";
                    break;
                case 3 :
                    kind = "%流行%";
                    break;
                case 4 :
                    kind = "%说唱%";
                    break;
                case 5 :
                    kind = "%民谣%";
                    break;
                case 6 :
                    kind = "%轻音乐%";
                    break;
                case 7 :
                    kind = "%原声%";
                    break;
                case 8 :
                    kind = "%电子%";
                    break;
                case 9 :
                    kind = "%拉丁%";
                    break;
                case 10 :
                    kind = "%爵士%";
                    break;
                case 11 :
                    kind = "%世界音乐%";
                    break;
                case 12 :
                    kind = "%布鲁斯%";
                    break;
                case 13 :
                    kind = "%Electronic電子%";
                    break;
                case 14 :
                    kind = "%Funk/Soul/R&amp;B%";
                    break;
                case 15 :
                    kind = "%雷鬼%";
                    break;
                case 16 :
                    kind = "%Soundtrack原聲%";
                    break;
                default:
                    kind = null;
            }
        }

        if (page>0 && limit>0)
            return mapper.selectByWhere(name, singer, time, tag, kind, (page-1)*limit, limit);

        return mapper.selectByWhere(name, singer, time, tag, kind, null, null);
    }

    @Override
    public int searchCount(String name, String singer, String time, String tag, String kind) {

        if (name!=null && !"".equals(name.trim()))
            name = "%"+name+"%";
        else name=null;

        if (singer!=null && !"".equals(singer.trim()))
            singer = "%"+singer+"%";
        else singer=null;

        if (time!=null && !"".equals(time.trim()))
            time = "%"+time+"%";
        else time=null;

        if (tag!=null && !"".equals(tag.trim()))
            tag = "%"+tag+"%";
        else tag=null;

        if (kind!=null && !"".equals(kind.trim())){
            switch(Integer.valueOf(kind)){
                case -1 :
                    kind = null;
                    break;
                case 0 :
                    kind = "%古典%";
                    break;
                case 1 :
                    kind = "%摇滚%";
                    break;
                case 2 :
                    kind = "%放克/灵歌/R&amp;B%";
                    break;
                case 3 :
                    kind = "%流行%";
                    break;
                case 4 :
                    kind = "%说唱%";
                    break;
                case 5 :
                    kind = "%民谣%";
                    break;
                case 6 :
                    kind = "%轻音乐%";
                    break;
                case 7 :
                    kind = "%原声%";
                    break;
                case 8 :
                    kind = "%电子%";
                    break;
                case 9 :
                    kind = "%拉丁%";
                    break;
                case 10 :
                    kind = "%爵士%";
                    break;
                case 11 :
                    kind = "%世界音乐%";
                    break;
                case 12 :
                    kind = "%布鲁斯%";
                    break;
                case 13 :
                    kind = "%Electronic電子%";
                    break;
                case 14 :
                    kind = "%Funk/Soul/R&amp;B%";
                    break;
                case 15 :
                    kind = "%雷鬼%";
                    break;
                case 16 :
                    kind = "%Soundtrack原聲%";
                    break;
                default:
                    kind = null;
            }
        }
        return mapper.countSeletcByWhere(name, singer, time, tag, kind);
    }

    @Override
    public TwoData analysis(String type) {
        TwoData data = new TwoData();
        switch (type){
            case "compare":
                data.setData1(mapper.compare1());
                data.setData2(mapper.compare2());
                break;
            case "publisher":
                data.setData1(mapper.publisher());
                break;
            case "singer":
                data.setData1(mapper.singer());
                break;
            case "medium":
                data.setData1(mapper.medium());
                break;
            case "kind":
                data.setData1(mapper.kind1());
                data.setData2(mapper.kind2());
                break;
        }

        data.setMsg(null);
        data.setCode(1000);
        return data;
    }
}
