package com.kainiu.mall.mapper;

import com.kainiu.mall.entity.*;
import com.kainiu.mall.util.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 微信小程序-商城
 * 2019-11-29
 */
@Repository
public interface MallControllerMapper {
    // 商品类型查询(只查前八条)
    //rows:前多少条
    public List<GoodsType> mallClassify(@Param("rows") Integer rows);

    /**
     * 商品列表查询
     * keys:关键字
     * typeId:商品类型id
     * pageBean:列表分页
     */
    public List<GoodsInfo> goodsListQuery(@Param("keys") String keys,@Param("typeId") String typeId
            ,@Param("pageBean") PageBean pageBean);

    /**
     * 商品明细
     * goodsId:商品id
     */
    public GoodsInfo goodsDetailQuery(@Param("goodsId")String goodsId);


    /**
     * 查找收货地址
     * webchatNo：微信号，
     * addrId：收货地址id
     */
    public Address defaultAddressQuery(@Param("webchatNo") String webchatNo,@Param("addrId") String addrId);

    /**
     * 通过微信号进行收货地址查询
     * webchatNo：微信号
     */
    public List<Address> addressQuery(@Param("webchatNo") String webchatNo);

    /**
     * 修改用户积分
     * webchatNo:用户id
     * integral:积分数量
     */
    public void updateIntegral(@Param("webchatNo") String webchatNo,@Param("integral") Integer integral);

    /**
     * 创建新订单
     * godsOrder:商品订单
     */
    public void newOrder(@Param("godsOrder") GodsOrder godsOrder);

    /**
     * 获取表字段最大ID
     * uid:表字段id名称
     * tableName:表名称
     */
    public String biggestId(@Param("uid") String uid,@Param("tableName") String tableName);

    /**
     * 通道订单编号查看订单详情
     * orderCode：订单id
     */
    public OrderDetails orderDetailsQuery(@Param("orderCode") String orderCode);

    /*
    *判断积分是否购买该商品
    * webchatNo:用户id
    * integral:积分数量
    * */
    public Integer integralBalance(@Param("webchatNo") String webchatNo,@Param("integral") Integer integral);

    /*
     *新增积分消费记录
     * webchatNo:用户id
     * integral:积分数量
     * */
    public Integer newRecord(@Param("integral") Integer integral,@Param("orderCode") String  orderCode,@Param("webchatNo") String webchatNo);

}
