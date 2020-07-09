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
    private int state;
    private String message;

}
