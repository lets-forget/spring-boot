package com.kang.controller;

import com.kang.bean.*;
import com.kang.service.FavoriteService;
import com.kang.service.RouteService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private FavoriteService favoriteService;
    ResultInfo resultInfo=new ResultInfo();

    @RequestMapping("/selectByCid")
    public Page selectByCid(String cid, Integer currentPage,String rname){
        System.out.println("rname = " + rname);
        int id=0;
        if (currentPage==null||currentPage<=0) currentPage=1;
        if (cid!=null&&!cid.equals("undefined")) id=Integer.parseInt(cid);
        System.out.println(id);
        if (rname==null||"undefined".equals(rname)) rname=null;
        Integer pageSize=10;
        Page page = routeService.selectByListCid(id, currentPage, pageSize,rname);
        return page;
    }

    //最新旅游
    @RequestMapping("/selectDescAll")
    public List<Route> selectDescAll(){

        return routeService.selectDescAll();
    }

    //人气旅游
    @RequestMapping("/selectCountAll")
    public List<Route> selectCountAll(){

        return routeService.selectCountAll();
    }

    //主题旅游
    @RequestMapping("/selectthemeAll")
    public List<Route> selectthemeAll(){
        return routeService.selectthemeAll();
    }

    //根据rid查询数据
    @RequestMapping("/selectByRid")
    public Route selectByRid(Integer rid){
        return routeService.selectByRid(rid);
    }

    //点击收藏
    @RequestMapping("/intserFavorite")
    public ResultInfo intserFavorite(Integer rid,HttpSession session){
        Favorite favorite=new Favorite();
        User user = (User) session.getAttribute("user");
        favorite.setRid(rid);
        favorite.setUid(user.getUid());
        favorite.setDate(new Date());
        boolean isFavorite=routeService.insertFavorite(favorite);

        resultInfo.setFlag(isFavorite);
        return resultInfo;
    }

    //查看用户是否收藏
    @RequestMapping("/isFavorite")
    public ResultInfo isFavorite(Integer rid, HttpSession session){
        User user = (User) session.getAttribute("user");
        int uid=0;
        if (user!=null){
            uid=user.getUid();
        }
        boolean isIntser=favoriteService.isFavorite(rid,uid);
        resultInfo.setFlag(isIntser);
        return resultInfo;
    }

    //收藏排行榜
    @RequestMapping("/collectCount")
    public Page collectCount(Integer currentPage){
        if (currentPage==null) currentPage=1;
        Integer pageSize=8;
        Page page=routeService.selectListCount(currentPage,pageSize);
        return page;
    }
}
