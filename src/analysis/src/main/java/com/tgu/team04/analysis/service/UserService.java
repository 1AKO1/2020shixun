package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.LogMessage;
import com.tgu.team04.analysis.entity.User;

public interface UserService {
    LogMessage login(String uid, String pwd);
    User register(User user);
}
