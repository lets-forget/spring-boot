package com.kang.bean;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class Page {
    private PageInfo<?> pageInfo;
    private List<?> pageList;

}
