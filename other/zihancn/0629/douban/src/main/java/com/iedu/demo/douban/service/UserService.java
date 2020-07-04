package com.iedu.demo.douban.service;

import com.iedu.demo.douban.entity.User;

import java.util.List;

public interface UserService {
    User login(String uid,String pwd);

    User addUser(User user);

    List<User> search(User user , int page ,int limit);

    int searchCount(User user);

    boolean resetPwd(int id);

}
