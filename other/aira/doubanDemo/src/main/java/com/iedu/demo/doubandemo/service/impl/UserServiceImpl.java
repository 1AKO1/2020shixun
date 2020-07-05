package com.iedu.demo.doubandemo.service.impl;

import com.iedu.demo.doubandemo.dao.UserMapper;
import com.iedu.demo.doubandemo.entity.User;
import com.iedu.demo.doubandemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*  Service的具体作用：
*  1. 具体实现业务逻辑（完成具体工作）
*  2. 调用mapper层
*
* */
@Service
public class UserServiceImpl implements UserService {

    @Autowired // 自动组装
    private UserMapper mapper;

    @Override
    public User login(String uid, String pwd) {

        User user = mapper.login(uid, pwd);
        if (user != null && user.getState() == 1)
            return user;
        return null;
    }

    @Override
    public User addUser(User user) {

        if(mapper.selectByUid(user.getUid()) == null){
            user.setState(0);
            mapper.addUser(user);
            return user;
        }

        return null;
    }

    @Override
    public List<User> search(User user, int page, int limit) {
        if(user != null && !"".equals(user.getUid().trim())){ // 给用户加上模糊查询的东西
            user.setUid("%"+ user.getUid() +"%");
        }

        if (page > 0 && limit > 0){
            return mapper.selectByWhere(user, (page - 1)*limit, limit);
        }

        return mapper.selectByWhere(user, null, null);
    }

    @Override
    public int searchCount(User user) {
        return mapper.countSelectByWhere(user);
    }

    @Override
    public boolean resetPwd(int id) {

        String defaultPwd = "00000000";

        if(mapper.updatePwd(id, defaultPwd) == 1)
            return true;
        return false;
    }


}
