package com.kainiu.mall.mapper;

import com.kainiu.mall.entity.Address;
import com.kainiu.mall.entity.OrderList;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.util.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 微信小程序-个人中心
 * 2019-12-4
 */
@Repository
public interface PersonalCenterMapper {

    /**
     * 通过订单状态查询订单列表
     * status：订单状态
     */
    public List<OrderList> orderListQuery(@Param("status") Integer status,@Param("webchatNo") String webchatNo,@Param("pageBean") PageBean pageBean);


    /**
     * 修改订单状态
     *orderCode:订单ID
     */
    public void modifyState(@Param("orderCode")String orderCode);

    /**
     * 删除收货地址
     * addrId：收货地址ID
     */
    public void cancelShippingAddress(@Param("addrId")String addrId);

    /**
     * 新增收货地址
     * address:收货地址实体类
     */
    public void appendShippingAddress(@Param("address") Address address);

    /**
     * 编辑收货地址
     * address:收货地址实体类
     */
    public void updateShippingAddress(@Param("address") Address address);

    /*
    * 更改收货默认地址
    * webchatNo: 微信号
    * addrId:收货地址id
    * */
    public void updateDefaultAddress(@Param("webchatNo") String webchatNo,@Param("addrId") String addrId);
}
