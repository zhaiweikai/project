package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;

//商户详情实体类
//2019-12-5
@Data
public class Merchants implements Serializable {
    private static final long serialVersionUID = 1L;
    private String locationId;//id
    private String merchantName;//商户姓名
    private String location;//位置说明
    private double distance;//距离
    private String distanceUnit;//距离单位
    private double lon;//经度
    private double lat;//纬度
    private String telephone;//联系电话
    private String businessHours;//营业时间
    private String storeIntroduce;//店铺介绍
    private String shopImg;//店铺图片地址
}
