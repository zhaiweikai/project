package com.kainiu.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kainiu.mall.RepeatSubmit.NoRepeatSubmit;
import com.kainiu.mall.entity.GodsOrder;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.service.MallControllerService;
import com.kainiu.mall.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信小程序-商城
 * 2019-11-29
 */
@RestController
@RequestMapping("/wx/mall")
public class MallController {
    private static Logger log = LoggerFactory.getLogger(MallController.class);

    @Resource
    private MallControllerService mallControllerService;
    // 商品类型查询(只查前八条)
    //rows:前多少条
    @PostMapping("/mallClassify")
    @ResponseBody
    public ResultData mallClassify(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        Integer rows =(Integer) jsonParam.get("rows");

//        log.info("[com.kainiu.mall.controller.MallController.mallClassify]"
//                +"rows:"+rows);
//        log.info("[com.kainiu.mall.controller.MallController.mallClassify]"
//                +mallControllerService.mallClassify(rows));
        return mallControllerService.mallClassify(rows);
    }

    /**
     * 商品列表查询
     * keys:关键字
     * typeId：商品类型id
     * page：页数
     * rows：显示行数
     */
    @PostMapping("/goodsListQuery")
    @ResponseBody
    public ResultData goodsListQuery(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        String keys =(String) jsonParam.get("keys");
        String typeId =(String) jsonParam.get("typeId");
        Integer page =(Integer) jsonParam.get("page");
        Integer rows =(Integer) jsonParam.get("rows");

        //设置查询开始页数、行数
        PageBean pageBean = new PageBean();
        pageBean.setPage(page);
        pageBean.setRows(rows);

//        log.debug("[com.kainiu.mall.controller.MallController.goodsListQuery]"
//                +"keys:"+keys+",typeId"+typeId+",page:"+page+",rows"+rows);
//        log.info("[com.kainiu.mall.controller.MallController.goodsListQuery]"
//                +mallControllerService.goodsListQuery(keys,typeId,pageBean));

        return mallControllerService.goodsListQuery(keys,typeId,pageBean);
    }

    /**
     * 商品明细
     * goodsId:商品id
     */
    @PostMapping("/goodsDetailQuery")
    @ResponseBody
    public ResultData goodsDetailQuery(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        String goodsId =(String) jsonParam.get("goodsId");

//        log.debug("[com.kainiu.mall.controller.MallController.goodsDetailQuery]"
//                +"goodsId:"+goodsId);
//        log.info("[com.kainiu.mall.controller.MallController.goodsDetailQuery]"
//                +mallControllerService.goodsDetailQuery(goodsId));

        return mallControllerService.goodsDetailQuery(goodsId);
    }

    /**
     * 查找收货地址
     * webchatNo：微信号，
     * addrId：收货地址id
     */
    @PostMapping("/defaultAddressQuery")
    @ResponseBody
    public ResultData defaultAddressQuery(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        String webchatNo =(String) jsonParam.get("webchatNo");
        String addrId =(String) jsonParam.get("addrId");

//        log.debug("[com.kainiu.mall.controller.MallController.defaultAddressQuery]"
//                +"webchatNo:"+webchatNo+",addrId:"+addrId);
//        log.info("[com.kainiu.mall.controller.MallController.defaultAddressQuery]"
//                +mallControllerService.defaultAddressQuery(webchatNo,addrId));

        return mallControllerService.defaultAddressQuery(webchatNo,addrId);
    }

    /**
     * 通过微信号进行收货地址查询
     * webchatNo:微信号
     */
    @PostMapping("/addressQuery")
    @ResponseBody
    public ResultData addressQuery(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        String webchatNo =(String) jsonParam.get("webchatNo");

//        log.debug("[com.kainiu.mall.controller.MallController.addressQuery]"
//                +"webchatNo:"+webchatNo);
//        log.info("[com.kainiu.mall.controller.MallController.addressQuery]"
//                +mallControllerService.addressQuery(webchatNo));

        return mallControllerService.addressQuery(webchatNo);
    }

    /**
     * 创建新订单
     *webchatNo:微信号
     * integralReal:实付积分
     * integral：单品积分
     * nums：数量
     * freight：运费
     * goodsId：商品id
     * addrId：地址id
     * typeId：商品分类ID
     */
    @PostMapping("/createOrder")
    @ResponseBody
    @NoRepeatSubmit(lockTime = 1000*10)
    public ResultData createOrder(@RequestBody JSONObject jsonParam){
        //获取新增条件
        String webchatNo =(String) jsonParam.get("webchatNo");
        Integer integralReal =(Integer) jsonParam.get("integralReal");
        Integer integral =(Integer) jsonParam.get("integral");
        Integer nums =(Integer) jsonParam.get("nums");
        double freight =Double.valueOf(String.valueOf(jsonParam.get("freight")));
        String goodsId =String.valueOf(jsonParam.get("goodsId"));
        String addrId =String.valueOf(jsonParam.get("addrId"));
        String typeId =String.valueOf(jsonParam.get("typeId"));

//        log.debug("[com.kainiu.mall.controller.MallController.addressQuery]"
//                +"webchatNo:"+webchatNo+",integralReal:"+integralReal+",integral:"+integral
//                +",nums"+nums+",freight"+freight+",goodsId"+goodsId+",addrId"+addrId+",typeId"+typeId);

        GodsOrder godsOrder = new GodsOrder();
        godsOrder.setIntegralReal(integralReal);
        godsOrder.setIntegral(integral);
        godsOrder.setNums(nums);
        godsOrder.setFreight(freight);
        godsOrder.setGoodsId(goodsId);
        godsOrder.setAddrId(addrId);
        godsOrder.setWebchatNo(webchatNo);

        return mallControllerService.createOrder(godsOrder,integralReal,typeId);
    }

    /**
     * 通道订单编号查看订单详情
     * orderCode：订单id
     */
    @PostMapping("/orderDetailsQuery")
    @ResponseBody
    public ResultData orderDetailsQuery(@RequestBody JSONObject jsonParam){
        //获取查询条件
        String orderCode =String.valueOf(jsonParam.get("orderCode"));

//        log.debug("[com.kainiu.mall.controller.MallController.orderDetailsQuery]"
//                +"orderCode:"+orderCode);
//        log.info("[com.kainiu.mall.controller.MallController.orderDetailsQuery]"
//                +mallControllerService.orderDetailsQuery(orderCode));

        return mallControllerService.orderDetailsQuery(orderCode);
    }



}

