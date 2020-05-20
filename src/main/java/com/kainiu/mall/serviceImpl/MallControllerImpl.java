package com.kainiu.mall.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.kainiu.mall.controller.MallController;
import com.kainiu.mall.entity.Address;
import com.kainiu.mall.entity.GodsOrder;
import com.kainiu.mall.entity.OrderDetails;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.mapper.MallControllerMapper;
import com.kainiu.mall.service.MallControllerService;
import com.kainiu.mall.util.ResultData;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * 微信小程序-商城
 * 2019-11-29
 */
@Service
public class MallControllerImpl implements MallControllerService {
    private static Logger log = LoggerFactory.getLogger(MallControllerImpl.class);
    @Autowired
    private MallControllerMapper mallControllerMapper;

    // 商品类型查询(只查前八条)
    //rows:前多少条
    @Override
    public ResultData mallClassify(Integer rows) {

//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.mallClassify]"
//                +"rows:"+rows);
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.mallClassify]"
//                +mallControllerMapper.mallClassify(rows));
        return ResultData.ok(mallControllerMapper.mallClassify(rows));
    }

    /**
     * 商品列表查询
     * keys:关键字
     * typeId:商品类型id
     * pageBean:列表分页
     */
    @Override
    @Cacheable(value = "goodsinfo",key = "'goodsinfo'")
    public ResultData goodsListQuery(String keys, String typeId, PageBean pageBean){
        System.out.println("没有进入缓存");
        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.goodsListQuery]"
                +"keys:"+keys+",typeId:"+typeId+",pageBean"+pageBean);
        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.goodsListQuery]"
                +mallControllerMapper.goodsListQuery(keys,typeId,pageBean));
        System.out.println(mallControllerMapper.goodsListQuery(keys,typeId,pageBean));
        return ResultData.ok(mallControllerMapper.goodsListQuery(keys,typeId,pageBean));
    }
    /**
     * 商品明细
     * goodsId:商品id
     */
    @Override
    public ResultData goodsDetailQuery(String goodsId){

//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.goodsDetailQuery]"
//                +"goodsId:"+goodsId);
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.goodsDetailQuery]"
//                +mallControllerMapper.goodsDetailQuery(goodsId));
        return ResultData.ok(mallControllerMapper.goodsDetailQuery(goodsId));
    }

    /**
     * 查找收货地址
     * webchatNo：微信号，
     * addrId：收货地址id
     */
    @Override
    public ResultData defaultAddressQuery(String webchatNo, String addrId){
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.defaultAddressQuery]"
//                +"webchatNo:"+webchatNo+",addrId:"+addrId);
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.defaultAddressQuery]"
//                +mallControllerMapper.defaultAddressQuery(webchatNo,addrId));

        return ResultData.ok(mallControllerMapper.defaultAddressQuery(webchatNo,addrId));
    }

    /**
     * 通过微信号进行收货地址查询
     * webchatNo：微信号
     */
    @Override
    public ResultData addressQuery(String webchatNo){
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.addressQuery]"
//                +"webchatNo:"+webchatNo);
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.addressQuery]"
//                +mallControllerMapper.addressQuery(webchatNo));

        return ResultData.ok(mallControllerMapper.addressQuery(webchatNo));
    }

    /**
     * 生成订单
     * godsOrder:商品订单实体类
     * webchatNo:微信号
     * integral：积分数量
     * typeId:商品分类id
     */
    @Override
    public ResultData createOrder(GodsOrder godsOrder,Integer integral,String typeId){
        JSONObject jsonObject=new JSONObject();
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.createOrder]"
//                +"godsOrder:"+godsOrder+",integral"+integral);

        //判断积分足够
        Integer balance =mallControllerMapper.integralBalance(godsOrder.getWebchatNo(),integral);
        if(balance<=0){

            return ResultData.error("积分余额不足！");
        }


        //获取日期字符串
        Date d = new Date();
//        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String dateNowStr = sdf.format(d);

        //获取最大订单编号
        String uid =mallControllerMapper.biggestId("order_code","c_goods_order");
//        System.out.println(uid);
        String newId="0001";
        if (uid != "" && uid!=null) {
            newId=uid.substring(uid.length()-4);
        }

        //生成订单编号
        godsOrder.setOrderCode(typeId+dateNowStr+String.format("%04d", (Integer.parseInt(newId)+1)));
        //生成订单

        mallControllerMapper.newOrder(godsOrder);

        //修改积分
        mallControllerMapper.updateIntegral(godsOrder.getWebchatNo(),integral);


        //新增消费记录
        Integer score =integral-integral*2;
        mallControllerMapper.newRecord(score,godsOrder.getOrderCode(),godsOrder.getWebchatNo());


        //返回订单id

        jsonObject.put("orderCode",godsOrder.getOrderCode());

        return ResultData.ok(jsonObject);
    }

    /**
     * 通道订单编号查看订单详情
     * orderCode：订单id
     */
    @Override
    public ResultData orderDetailsQuery(String orderCode){

//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.orderDetailsQuery]"
//                +"orderCode:"+orderCode);
//        log.debug("[com.kainiu.mall.serviceImpl.MallControllerImpl.orderDetailsQuery]"
//                +mallControllerMapper.orderDetailsQuery(orderCode));

        return ResultData.ok(mallControllerMapper.orderDetailsQuery(orderCode));
    }
}
