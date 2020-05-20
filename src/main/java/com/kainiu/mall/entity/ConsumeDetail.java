package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//微信小程序-积分消费记录
//2019-12-5
@Data
public class ConsumeDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cdId;//积分消费记录id
    private Integer integrate;//增减积分
    private String webchatNo;//微信号
    private String orderCode;//订单编号
    private String goodsName;//商品名称
    private String describ;//描述
    private String picture;//商品图片
    private String mobilePhone;//商户id
    private String merchantsName;//商户名称
    private String merchantsIcon;//商户图标
    private LocalDateTime createTime;//创建时间
}
