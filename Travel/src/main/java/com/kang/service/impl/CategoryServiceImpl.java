package com.kang.service.impl;

import com.github.pagehelper.PageHelper;
import com.kang.bean.Category;
import com.kang.bean.Page;
import com.kang.mapper.CategoryMapper;
import com.kang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

}
