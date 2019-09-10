package com.kang.service;

import com.kang.bean.User;

import javax.mail.MessagingException;

public interface UserService {
    //根据用户名查询
    User selectByName(String username);
    //注册
    boolean registerUser(User user) throws MessagingException;

    boolean updatecheckCode(String code);
}
