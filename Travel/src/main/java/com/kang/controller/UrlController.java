package com.kang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/header")
    public String header(){
        return "header.html";
    }

    @RequestMapping("/footer")
    public String footer(){
        return "footer";
    }

    @RequestMapping("/register_ok")
    public String register_ok(){
        return "register_ok";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/route_list")
    public String route_list(){
        return "route_list";
    }


    @RequestMapping("/favoriterank")
    public String favoriterank(){
        return "favoriterank";
    }

    @RequestMapping("/myfavorite")
    public String myfavorite(){
        return "myfavorite";
    }

    @RequestMapping("/route_detail")
    public String route_detail(){
        return "route_detail";
    }
}
