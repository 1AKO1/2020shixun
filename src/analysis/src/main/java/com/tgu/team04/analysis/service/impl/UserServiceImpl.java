package com.tgu.team04.analysis.service.impl;

import com.tgu.team04.analysis.dao.UserMapper;
import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.User;
import com.tgu.team04.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User login(String uid, String pwd) {

        User user = mapper.login(uid,pwd);
        if (user == null || user.getState()==0)
            user=null;

        return user;
    }

    @Override
    public TableData register(User user) {

            TableData data = new TableData();
        if (mapper.selectByUid(user.getUid()) != null){
            data.setCode(2000);
            data.setData(null);
            data.setMsg("用户名已存在");
            return data;
        }

//        if (user.getAge() <= 10 || user.getAge() >= 70){
//            user.setState(0);
//            return user;
//        }
//
//        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
//        Pattern pattern;
//        Matcher matcher;
//        pattern = Pattern.compile(rule);
//        matcher = pattern.matcher(user.getEmail());
//        if (user.getEmail()!=null && !matcher.matches()){
//            user.setState(0);
//            return user;
//        }


        user.setState(1);
        mapper.register(user);
        data.setMsg("注册成功");
        data.setCode(1000);
        data.setData(null);
        return data;

    }

    @Override
    public List<User> search(User user, int page, int limit) {

        if (user!=null && !"".equals(user.getUid().trim()))
            user.setUid("%"+user.getUid()+"%");

        if (user!=null && !"".equals(user.getNickName().trim()))
            user.setNickName("%"+user.getNickName()+"%");
        if (page>0 && limit>0)
            return mapper.selectByWhere(user, (page-1)*limit, limit);

        return mapper.selectByWhere(user, null, null);
    }

    @Override
    public boolean pwdReset(int id) {

        String defaultPwd="0000";
        if (mapper.updatePwd(id,defaultPwd)==1)
            return true;

        return false;
    }

    @Override
    public int searchCount(User user) {
        return mapper.countSeletcByWhere(user);
    }
}

