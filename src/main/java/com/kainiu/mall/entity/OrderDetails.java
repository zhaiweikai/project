package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;

//订单详情实体类
//2019-12-3
@Data
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    public GoodsInfo goodsInfo;//商品详情实体类
    public GodsOrder goodsOrder;//订单详情实体类
    public Address address;//收货地址实体类
}
