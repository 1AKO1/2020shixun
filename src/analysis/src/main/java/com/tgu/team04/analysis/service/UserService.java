package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.User;

import java.util.List;

public interface UserService {
    TableData login(String uid, String pwd);

    TableData register(User user);

    List<User> search(User user, int page, int limit);

    boolean pwdReset(int id);

    int searchCount(User user);

}
