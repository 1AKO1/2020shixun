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
    public List<User> search(String uid, String nickName,Integer state, Integer page, Integer limit) {

        if (uid!=null && !"".equals(uid.trim()))
            uid = "%"+uid+"%";

        if (nickName!=null && !"".equals(nickName.trim()))
            nickName = "%"+nickName+"%";

        if (page>0 && limit>0)
            return mapper.selectByWhere(uid, nickName, state, (page-1)*limit, limit);

        return mapper.selectByWhere(uid ,nickName ,state, null, null);
    }

    @Override
    public boolean pwdReset(String uid,String newPwd) {

        if (mapper.updatePwd(uid,newPwd)==1)
            return true;

        return false;
    }

    @Override
    public int searchCount(String uid, String nickName, int state) {
        return mapper.countSeletcByWhere(uid, nickName, state);
    }

    @Override
    public boolean stateReset(String uid, Integer state) {
        if (mapper.stateReset(uid, state) == 1)
            return true;
        return false;
    }

}

