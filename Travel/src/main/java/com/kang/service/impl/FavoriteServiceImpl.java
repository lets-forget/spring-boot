package com.kang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kang.bean.Favorite;
import com.kang.bean.Page;
import com.kang.bean.Route;
import com.kang.mapper.FavoriteMapper;
import com.kang.service.FavoriteService;
import com.kang.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private RouteService routeService;

    @Override
    public boolean isFavorite(Integer rid, Integer uid) {
        Favorite favorite=favoriteMapper.selectByPrimaryKey(rid,uid);
        return favorite==null?false:true;
    }

    @Override
    public Page selectMyCollect(Integer uid, Integer currentPage, Integer pageSize) {
        Page page=new Page();
        
        List<Integer> parmas=new ArrayList<>();
        //通过uid查询rid
        List<Favorite> favorites = favoriteMapper.selectAll(uid);

        for (Favorite favorite : favorites) {
            parmas.add(favorite.getRid());
        }
        PageHelper.startPage(currentPage,pageSize);
        //通过rid查询商品
        List<Route> routes=routeService.selectByListRid(parmas);
        PageInfo<Route> pageInfo=new PageInfo<>(routes);
        page.setPageList(routes);
        page.setPageInfo(pageInfo);
        return page;
    }

    @Override
    public boolean deleteByRid(Integer rid) {
        Integer isdelete=favoriteMapper.deleteByRid(rid);
        if (isdelete>0){
            Integer isfavoirte=routeService.deleteFavoriteByRid(rid);
            if (isfavoirte>0){
                return true;
            }
        }
        return false;
    }


}
