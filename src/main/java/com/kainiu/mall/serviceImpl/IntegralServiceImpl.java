package com.kainiu.mall.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kainiu.mall.controller.MallController;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.mapper.IntegralMapper;
import com.kainiu.mall.service.IntegralService;
import com.kainiu.mall.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信小程序-积分
 * 2019-12-5
 */
@Service
public class IntegralServiceImpl implements IntegralService {
    private static Logger log = LoggerFactory.getLogger(MallController.class);
    @Autowired
    private IntegralMapper integralMapper;

    /**
     * 积分余额和消费记录查询
     * webchatNo:微信号
     * pageBean：分页
     */
    public ResultData integralQuery(String webchatNo, PageBean pageBean){
//        log.debug("[com.kainiu.mall.serviceImpl.IntegralServiceImpl.integralQuery]"
//                +"webchatNo:"+webchatNo+",pageBean:"+pageBean);
//        //获取积分余额
//        log.debug("[com.kainiu.mall.serviceImpl.IntegralServiceImpl.integralQuery]"
//                +integralMapper.integralBalance(webchatNo));
//
//        //消费记录列表
//        log.debug("[com.kainiu.mall.serviceImpl.IntegralServiceImpl.integralQuery]"
//                +integralMapper.expenseCalendar(webchatNo,pageBean));

        JSONObject jsonObject =new JSONObject ();
        jsonObject.put("remainingPoints",integralMapper.integralBalance(webchatNo));
        jsonObject.put("list", JSONArray.parseArray(JSON.toJSONString(integralMapper.expenseCalendar(webchatNo,pageBean))));


        return ResultData.ok(jsonObject);
    }
}
