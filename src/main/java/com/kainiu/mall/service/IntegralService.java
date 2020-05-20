package com.kainiu.mall.service;

import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.util.ResultData;

//微信小程序-积分
//2019-12-5
public interface IntegralService {

    /**
     * 积分余额和消费记录查询
     * webchatNo:微信号
     * pageBean：分页
     */
    public ResultData integralQuery(String webchatNo,PageBean pageBean);
}
