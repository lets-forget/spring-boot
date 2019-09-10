package com.kang.service;

import com.kang.bean.Favorite;
import com.kang.bean.Page;
import com.kang.bean.Route;

import java.util.Date;
import java.util.List;

public interface RouteService {
    public Page selectByListCid(Integer cid, Integer currentPage, Integer pageSize,String rname);

    List<Route> selectDescAll();

    List<Route> selectCountAll();

    List<Route> selectthemeAll();

    Route selectByRid(Integer rid);

    boolean insertFavorite(Favorite favorite);

    Page selectListCount(Integer currentPage,Integer pageSize);

    List<Route> selectByListRid(List<Integer> parmas);

    Integer deleteFavoriteByRid(Integer rid);
}
