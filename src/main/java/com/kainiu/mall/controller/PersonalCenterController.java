package com.kainiu.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.kainiu.mall.entity.Address;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.service.PersonalCenterService;
import com.kainiu.mall.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信小程序-个人中心
 * 2019-12-4
 */
@RestController
@RequestMapping("/wx/personalCenter")
public class PersonalCenterController {

    private static Logger log = LoggerFactory.getLogger(PersonalCenterController.class);

    @Resource
    private PersonalCenterService personalCenterService;

    /**
     * 通过订单状态查询订单列表
     * status：订单状态
     */
    @PostMapping("/orderListQuery")
    @ResponseBody
    public ResultData orderListQuery(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        Integer status =(Integer) jsonParam.get("status");
        String webchatNo =(String) jsonParam.get("webchatNo");
        Integer page =(Integer) jsonParam.get("page");
        Integer rows =(Integer) jsonParam.get("rows");

        //设置查询开始页数、行数
        PageBean pageBean = new PageBean();
        pageBean.setPage(page);
        pageBean.setRows(rows);

//        log.info("[com.kainiu.mall.controller.PersonalCenterController.orderListQuery]"
//                +"status:"+status+",webchatNo"+webchatNo+",page:"+page+",rows:"+rows);
//        log.info("[com.kainiu.mall.controller.PersonalCenterController.orderListQuery]"
//                +personalCenterService.orderListQuery(status,webchatNo,pageBean));
        return personalCenterService.orderListQuery(status,webchatNo,pageBean);
    }

    /**
     * 修改订单状态
     *orderCode:订单ID
     */
    @PostMapping("/modifyState")
    @ResponseBody
    public ResultData modifyState(@RequestBody JSONObject jsonParam){
        //获取查询条件
        String orderCode =(String) jsonParam.get("orderCode");

//        log.info("[com.kainiu.mall.controller.PersonalCenterController.modifyState]"
//                +"orderCode:"+orderCode);

        return personalCenterService.modifyState(orderCode);
    }

    /**
     * 删除收货地址
     * addrId：收货地址ID
     */
    @PostMapping("/cancelShippingAddress")
    @ResponseBody
    public ResultData cancelShippingAddress(@RequestBody JSONObject jsonParam){
        //获取查询条件
        String addrId =String.valueOf(jsonParam.get("addrId"));

//        log.info("[com.kainiu.mall.controller.PersonalCenterController.cancelShippingAddress]"
//                +"addrId:"+addrId);

        return personalCenterService.cancelShippingAddress(addrId);
    }

    /**
     * 编辑收货地址
     * addrId：收货地址ID
     * name: 收货人姓名
     * mobilePhone：收货人手机号
     * webchatNo：微信号
     * detailedAddress：详细地址
     */
    @PostMapping("/editReceivingAddress")
    @ResponseBody
    public ResultData editReceivingAddress(@RequestBody JSONObject jsonParam){
        //获取查询条件
        String addrId =(String) jsonParam.get("addrId");
        String name =(String) jsonParam.get("name");
        String mobilePhone =(String) jsonParam.get("mobilePhone");
        String webchatNo =(String) jsonParam.get("webchatNo");
        Boolean isDefault =(Boolean) jsonParam.get("isDefault");

        String detailedAddress =(String) jsonParam.get("detailedAddress");
//        log.info("[com.kainiu.mall.controller.PersonalCenterController.cancelShippingAddress]"
//                +"addrId:"+addrId+",name"+name+",mobilePhone"+mobilePhone+",webchatNo"+webchatNo
//                +",detailedAddress"+detailedAddress+",isDefault"+isDefault);

        //收货地址实体类赋值
        Address address =new Address();
        address.setAddrId(addrId);
        address.setName(name);
        address.setMobilePhone(mobilePhone);
        address.setWebchatNo(webchatNo);
        address.setAddress(detailedAddress);
        address.setIsDefault(isDefault==true?1:0);

        return personalCenterService.editReceivingAddress(address);
    }
}
