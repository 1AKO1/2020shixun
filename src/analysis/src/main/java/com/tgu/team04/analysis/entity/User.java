package com.tgu.team04.analysis.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String uid;
    private String pwd;
    private String nickName;
    private String email;
    private String address;
    private String sex;
    private Integer age;
    private int state;
}
