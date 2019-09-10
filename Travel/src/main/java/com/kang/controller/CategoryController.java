package com.kang.controller;

import com.kang.bean.Category;
import com.kang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //查询并记录当前页
    @RequestMapping("/selectAll")
    public List<Category> selectAllByCurrent(Integer cid){
        return categoryService.selectAll();
    }
}

