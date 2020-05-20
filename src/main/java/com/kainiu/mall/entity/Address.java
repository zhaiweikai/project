package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;

/**
 * 收货地址实体类
 * 2019-12-3
 */
@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    private String addrId;//收货地址id
    private String name;//姓名
    private String mobilePhone;//电话
    private String address;//地址
    private String webchatNo;//微信号
    private Integer isDefault;//是否默认
    private LocalDateTime createTime;//创建时间
    private LocalDateTime lastTime;//修改时间
}
