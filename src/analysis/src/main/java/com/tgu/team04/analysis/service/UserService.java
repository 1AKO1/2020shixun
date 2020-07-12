package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.TableData;
import com.tgu.team04.analysis.entity.User;

import java.util.List;

public interface UserService {
    User login(String uid, String pwd);

    TableData register(User user);

    List<User> search(String uid, String nickName,Integer state, Integer page, Integer limit);

    boolean pwdReset(String uid, String newPwd);

    int searchCount(String uid, String nickName, int state);

    boolean stateReset(String uid, Integer state);

    boolean deleteUser(String uid);

    User getCurrentUser(String uid);
}
