package com.kainiu.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//微信小程序-客户
//22019-12-5
@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String webchatNo; //微信号
    private String obilePhone;//手机号
    private String verificationCode;//验证码
    private String oldPwd;//旧密码
    private String newPwd;//新密码
    private Integer integralWallet; //钱包积分
    private LocalDateTime createTime;//创建时间


}
