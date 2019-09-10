package com.kang.controller;

import com.kang.bean.Page;
import com.kang.bean.ResultInfo;
import com.kang.bean.User;
import com.kang.service.FavoriteService;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    //我的收藏
    @RequestMapping("/mycollect")
    public Page mycollect(Integer currentPage, HttpSession session){
        if (currentPage==null) currentPage=1;
        Integer pageSize=12;
        //获取session域中的uid
        User user = (User) session.getAttribute("user");
        System.out.println("user = " + user);

        Page page =favoriteService.selectMyCollect(user.getUid(),currentPage,pageSize);
        return page;
    }

    //删除删除
    @RequestMapping("/deleteByRid")
    public boolean deleteByRid(Integer rid){
        boolean isDelete=favoriteService.deleteByRid(rid);
        return isDelete;
    }
}
