package com.tesla.demo.douban.service;

import com.tesla.demo.douban.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User login(String uid, String pwd);

}
