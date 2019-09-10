package com.kang.bean;

import lombok.Data;

import java.util.List;

@Data
public class Route {
    private Integer rid;

    private String rname;

    private Double price;

    private String routeintroduce;

    private String rflag;

    private String rdate;

    private String isthemetour;

    private Integer count;

    private Integer cid;

    private String rimage;

    private Integer sid;

    private String sourceid;

    private Category category;

    private Seller seller;

    private List<RouteImg> routeImgs;
}