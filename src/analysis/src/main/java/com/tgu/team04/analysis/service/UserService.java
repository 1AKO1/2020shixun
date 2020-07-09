package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.User;

public interface UserService {
    TableData login(String uid, String pwd);
    TableData register(User user);
}
