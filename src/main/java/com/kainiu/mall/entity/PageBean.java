package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;

//列表分页
//2019-11-30
@Data
public class PageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer page = 0;//页面
    private Integer rows = 5;//共多少行

}
