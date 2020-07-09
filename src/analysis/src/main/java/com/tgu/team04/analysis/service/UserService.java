package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.User;

public interface UserService {
    User login(String uid, String pwd);
    User register(User user);
}
