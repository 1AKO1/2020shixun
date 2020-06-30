package com.iedu.demo.douban.service;

import com.iedu.demo.douban.entity.User;

public interface UserService {
    User login(String uid,String pwd);
}
