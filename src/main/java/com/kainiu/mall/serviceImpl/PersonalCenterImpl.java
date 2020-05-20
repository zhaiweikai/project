package com.kainiu.mall.serviceImpl;

import com.kainiu.mall.controller.MallController;
import com.kainiu.mall.entity.Address;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.mapper.PersonalCenterMapper;
import com.kainiu.mall.service.PersonalCenterService;
import com.kainiu.mall.util.ResultData;
import com.kainiu.mall.util.SnowFlake;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信小程序-个人中心
 * 2019-12-4
 */
@Service
public class PersonalCenterImpl implements PersonalCenterService {

    private static Logger log = LoggerFactory.getLogger(MallController.class);

    @Autowired
    private PersonalCenterMapper personalCenterMapper;

    /**
     * 通过订单状态查询订单列表
     * status：订单状态
     */
    @Override
    public ResultData orderListQuery(Integer status,String webchatNo, PageBean pageBean){

//        log.debug("[com.kainiu.mall.serviceImpl.PersonalCenterImpl.orderListQuery]"
//                +"status:"+status+",webchatNo:"+webchatNo);
//        log.debug("[com.kainiu.mall.serviceImpl.PersonalCenterImpl.orderListQuery]"
//                +personalCenterMapper.orderListQuery(status,webchatNo,pageBean));
        return ResultData.ok(personalCenterMapper.orderListQuery(status,webchatNo,pageBean));
    }

    /**
     * 修改订单状态
     *orderCode:订单ID
     */
    @Override
    public ResultData modifyState(String orderCode){

//        log.debug("[com.kainiu.mall.serviceImpl.PersonalCenterImpl.modifyState]"
//                +"orderCode:"+orderCode);

        //修改订单状态
        personalCenterMapper.modifyState(orderCode);
        return ResultData.ok();
    }

    /**
     * 删除收货地址
     * addrId：收货地址ID
     */
    @Override
    public ResultData cancelShippingAddress(String addrId){

//        log.debug("[com.kainiu.mall.serviceImpl.PersonalCenterImpl.cancelShippingAddress]"
//                +"addrId:"+addrId);

        //删除收货地址
        personalCenterMapper.cancelShippingAddress(addrId);
        return ResultData.ok();
    }

    /**
     * 编辑收货地址
     * address:收货地址实体类
     */
    @Override
    public ResultData editReceivingAddress(Address address){

        //判断收货地址id是否为空
        if(address.getAddrId()!=""&&address.getAddrId()!=null){
            //修改操作
            personalCenterMapper.updateShippingAddress(address);
        }else{
            //新增操作
            //生成id
            SnowFlake snowFlake =new SnowFlake(1,1);
            address.setAddrId(String.valueOf(snowFlake.nextId()));

            personalCenterMapper.appendShippingAddress(address);
        }

        //判断是否设置默认地址
        if(address.getIsDefault()==1){
            //更改其他默认地址
            personalCenterMapper.updateDefaultAddress(address.getWebchatNo(),address.getAddrId());

        }

        return ResultData.ok();
    }

}
