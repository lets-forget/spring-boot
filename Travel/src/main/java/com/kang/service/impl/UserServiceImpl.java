package com.kang.service.impl;

import com.kang.bean.User;
import com.kang.config.ServerConfig;
import com.kang.mapper.UserMapper;
import com.kang.service.MailService;
import com.kang.service.UserService;
import com.kang.utils.UuidUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    @Autowired
    private ServerConfig serverConfig;

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean registerUser(User user) throws MessagingException {
        user.setStatus(0);
        user.setCode(UuidUtils.getUUID());

        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());

        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5", user.getPassword(), salt, 1024).toHex();

        user.setPassword(newPs);
        //判断是否有用户名
        User isUsername = userMapper.selectByName(user.getUsername());

        if (isUsername==null){
            int i = userMapper.insert(user);
            if (i>0){
                String text="<a href='http://"+serverConfig.getUrl()+"/user/checkCode?code="+user.getCode()+"'>恭喜您，注册成功，点击激活吧!!!</a>";
                //发送邮件 激活码  yjlarinxyteibicf
                mailService.sendMain(user.getEmail(),"激活",text);
                return true;
            }
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatecheckCode(String code) {
        int i=userMapper.updatecheckCode(code,1);
        return i>0?true:false;
    }
}
