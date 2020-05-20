package com.kainiu.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.kainiu.mall.entity.Address;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.util.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信小程序-个人中心
 * 2019-12-4
 */
public interface PersonalCenterService {

    /**
     * 通过订单状态查询订单列表
     * status：订单状态
     */
    public ResultData orderListQuery(Integer status,String webchatNo, PageBean pageBean);

    /**
     * 修改订单状态
     *orderCode:订单ID
     */
    public ResultData modifyState(String orderCode);

    /**
     * 删除收货地址
     * addrId：收货地址ID
     */
    public ResultData cancelShippingAddress(String addrId);

    /**
     * 编辑收货地址
     * address：收货地址实体类
     */
    public ResultData editReceivingAddress(Address address);


}
