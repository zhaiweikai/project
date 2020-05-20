package com.kainiu.mall.service;


import com.kainiu.mall.entity.Address;
import com.kainiu.mall.entity.GodsOrder;
import com.kainiu.mall.entity.OrderDetails;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.util.ResultData;
import org.apache.ibatis.annotations.Param;

/**
 * 微信小程序-商城
 * 2019-11-29
 */
public interface MallControllerService {

    //商品类型查询(只查前八条)
    //rows:前多少条
    public ResultData mallClassify(Integer rows);

    /**
     * 商品列表查询
     * keys:关键字
     * typeId:商品类型id
     * pageBean:列表分页
     */
    public ResultData goodsListQuery(String keys, String typeId, PageBean pageBean);

    /**
     * 商品明细
     * goodsId:商品id
     */
    public ResultData goodsDetailQuery(String goodsId);

    /**
     * 查找收货地址
     * webchatNo：微信号，
     * addrId：收货地址id
     */
    public ResultData defaultAddressQuery(String webchatNo, String addrId);


    /**
     * 通过微信号进行收货地址查询
     * webchatNo：微信号
     */
    public ResultData addressQuery(String webchatNo);

    /**
     * 生成订单
     * godsOrder:商品订单实体类
     * webchatNo:微信号
     * integral：积分数量
     */
    public ResultData createOrder(GodsOrder godsOrder,Integer integral,String typeId);

    /**
     * 通道订单编号查看订单详情
     * orderCode：订单id
     */
    public ResultData orderDetailsQuery(String orderCode);
}
