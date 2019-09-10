package com.kang.controller;

import com.kang.bean.ResultInfo;
import com.kang.bean.User;
import com.kang.service.UserService;
import com.kang.utils.UuidUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    ResultInfo resultInfo=new ResultInfo();

    @Autowired
    private UserService userService;

    //登录
    @RequestMapping("/login")
    public ResultInfo login(User user, HttpSession session,String code,boolean rememberMe){
        String  sessioncode = (String) session.getAttribute("session_code");
        if (code.equals(sessioncode)){
            //获取subject实例
            Subject subject = SecurityUtils.getSubject();
                //将用户名和密码封装到UsernamePasswordToken
            if (!subject.isAuthenticated()){
                UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword(),rememberMe);
                try{
                    subject.login(token);
                    if (subject.isAuthenticated()){
                        resultInfo.setErrorMsg("登录成功");
                        resultInfo.setFlag(true);
                    }
                }catch (Exception e){
                    resultInfo.setErrorMsg(e.getMessage());
                    resultInfo.setFlag(false);
                }

            }else{
                resultInfo.setErrorMsg("您已登录过，无须重复登录");
                resultInfo.setFlag(false);
            }

        }else {
            resultInfo.setErrorMsg("验证码错误");
            resultInfo.setFlag(false);
        }

        session.removeAttribute("session_code");

        return resultInfo;
    }

    //注册
    @RequestMapping("/register")
    public ResultInfo register(User user,HttpSession session,String code) throws MessagingException {
        String  sessioncode = (String) session.getAttribute("session_code");
        if (code.equals(sessioncode)){
            boolean isregister = userService.registerUser(user);
            if (isregister){
                resultInfo.setFlag(isregister);
                resultInfo.setErrorMsg("注册成功");
            }else {
                resultInfo.setFlag(isregister);
                resultInfo.setErrorMsg("注册失败");
            }

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        }
        session.removeAttribute("session_code");
        return resultInfo;
    }

    //激活
    @RequestMapping("/checkCode")
    public ResultInfo checkCode(String code){
        boolean isCheck=userService.updatecheckCode(code);
        if (isCheck){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("激活成功");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("激活失败");
        }
        return resultInfo;
    }

    //回显用户名
    @RequestMapping("/echoName")
    public ResultInfo echoName(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null){
            resultInfo.setFlag(false);
        }else{
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg(user.getUsername());
        }
        return resultInfo;
    }

    @RequestMapping("/isUsername")
    public ResultInfo isUsername(String username){
        User user = userService.selectByName(username);
        if (user==null)resultInfo.setFlag(true);
        else resultInfo.setFlag(false);
        return resultInfo;
    }
}
