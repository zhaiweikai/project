package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品订单实体类
 * 2019-12-3
 */
@Data
public class GodsOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderCode;//订单编号
    private String remark;//订单备注
    private Integer integral;//单品积分
    private Integer integralReal;//实付积分
    private Integer nums;//数量
    private double freight;//运费
    private Integer status;//状态（1待发货2待收货3已完成）
    private String addrId;//地址id
    private String goodsId;//商品编号ID
    private String logisCode;//物流信息单号
    private String webchatNo;//微信号
    private LocalDateTime createTime;//创建时间
    private LocalDateTime paymentTime;//付款时间
    private LocalDateTime deliverTime;//发货时间
    private LocalDateTime finishTime;//完成时间
}
