package com.iedu.demo.doubandemo.service;

import com.iedu.demo.doubandemo.entity.User;

import java.util.List;

/*
* Service接口功能：定义业务逻辑的接口（定义职责，标识能力要求）
* */
public interface UserService {

    User login(String uid, String pwd);

    User addUser(User user);

    List<User> search(User user, int page, int limit);

    int searchCount(User user);

    boolean resetPwd(int id);
}
