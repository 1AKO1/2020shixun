package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String uid;
    private String pwd;
    private String pwd2;
    private String nickName;
    private String email;
    private String address;
    private String sex;
    private int age;

    public User(String pwd2, String uid, String pwd, String nickName, String email, String address, String sex, int age) {
        this.uid = uid;
        this.pwd = pwd;
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.sex = sex;
        this.age = age;
        this.pwd2 = pwd2;
    }
}
