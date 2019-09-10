package com.kang.service;

import com.kang.bean.Page;

public interface FavoriteService {
    public boolean isFavorite(Integer rid,Integer uid);

    Page selectMyCollect(Integer uid, Integer currentPage, Integer pageSize);

    boolean deleteByRid(Integer rid);
}
