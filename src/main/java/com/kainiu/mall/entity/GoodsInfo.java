package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品详情实体类
 * 2019-11-29
 */
@Data
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String goodsId;//商品详情id
    private String name;//商品名称
    private String gkey;//关键字
    private int integral;//积分
    private String brand;//商品品牌
    private String describ;//商品描述
    private int nums;//数量
    private String specs;//商品规格
    private String picture;//商品主图片位置（单列表）
    private String pictureBig;//商品主图片位置（双列表）
    private String pictureMain;//商品轮播图片位置
    private String pictureDescribe;//商品详情
    private int isUc;//是否下架（1在售2下架）
    private String typeId;//商品类型ID（一类）
    private LocalDateTime createTime;//创建时间
    private LocalDateTime lastTime;//修改时间
    private int changeNumber;//销量
}
