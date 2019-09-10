package com.kang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kang.bean.*;
import com.kang.mapper.*;
import com.kang.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private RouteImgMapper routeImgMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;
    @Override
    public Page selectByListCid(Integer cid, Integer currentPage, Integer pageSize,String rname) {
        Page page=new Page();
        PageHelper.startPage(currentPage,pageSize);
        List<Route> list = routeMapper.selectAll(cid,rname);
        PageInfo<Route> pageInfo=new PageInfo<>(list);
        page.setPageInfo(pageInfo);
        page.setPageList(list);
        return page;
    }

    @Override
    public List<Route> selectDescAll() {
        return routeMapper.selectDescAll();
    }

    @Override
    public List<Route> selectCountAll() {
        return routeMapper.selectCountAll();
    }

    @Override
    public List<Route> selectthemeAll() {
        return routeMapper.selectthemeAll();
    }

    @Override
    public Route selectByRid(Integer rid) {

        Route route=routeMapper.selectByRid(rid);

        //查询店信息
        Seller seller=sellerMapper.selectByPrimaryKey(route.getSid());
        route.setSeller(seller);

        Category category = categoryMapper.selectByPrimaryKey(route.getCid());
        route.setCategory(category);

        //根据rid查询img
        List<RouteImg> imgList=routeImgMapper.selectAllByRid(route.getRid());
        route.setRouteImgs(imgList);

        return route;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertFavorite(Favorite favorite) {

        Integer isInsert = favoriteMapper.insert(favorite);
        if (isInsert>0){
            Integer isUpdate=routeMapper.updateByCountByRid(favorite.getRid());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Page selectListCount(Integer currentPage,Integer pageSize) {
        Page page=new Page();

        PageHelper.startPage(currentPage,pageSize);
        List<Route> routes=routeMapper.selectListCount();

        PageInfo<Route> pageInfo=new PageInfo<>(routes);

        page.setPageList(routes);
        page.setPageInfo(pageInfo);
        return page;
    }

    //多个id查询商品
    @Override
    public List<Route> selectByListRid(List<Integer> parmas) {

        return routeMapper.selectByListRid(parmas);
    }

    @Override
    public Integer deleteFavoriteByRid(Integer rid) {
        return routeMapper.deleteFavorirteByRid(rid);
    }
}
