package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单列表实体类
 * 2019-12-4
 */
@Data
public class OrderList implements Serializable {
    private static final long serialVersionUID = 1L;
    public GoodsInfo goodsInfo;//商品详情实体类
    public GodsOrder goodsOrder;//订单详情实体类
}
