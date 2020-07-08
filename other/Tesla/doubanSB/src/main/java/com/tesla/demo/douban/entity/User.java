package com.tesla.demo.douban.entity;

import lombok.Data;

@Data
public class User {

    private int id; // 在数据库里的id
    private String uid; // 用户id
    private String pwd; // 用户密码
    private String nickName;    // 用户昵称

    public User(String uid, String pwd) {
        this.uid = uid;
        this.pwd = pwd;
    }

}
