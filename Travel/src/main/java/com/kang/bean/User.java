package com.kang.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer uid;

    private String username;

    private String password;

    private String name;

    private String birthday;

    private String sex;

    private String telephone;

    private String email;

    private Integer status;

    private String code;

}