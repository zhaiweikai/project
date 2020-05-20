package com.kainiu.mall.controller;

import com.alibaba.fastjson.JSONObject;

import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.service.IntegralService;
import com.kainiu.mall.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信小程序-积分
 * 2019-12-5
 */
@RestController
@RequestMapping("/wx/integral")
public class IntegralController {
    private static Logger log = LoggerFactory.getLogger(IntegralController.class);

    @Resource
    private IntegralService integralService;

    /**
     * 积分页面数据获取
     * webchatNo：微信号
     * page：页数
     * rows：显示行数
     */
    @PostMapping("/integralQuery")
    @ResponseBody
    public ResultData integralQuery(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        String webchatNo =(String) jsonParam.get("webchatNo");
        Integer page =(Integer) jsonParam.get("page");
        Integer rows =(Integer) jsonParam.get("rows");

        //设置查询开始页数、行数
        PageBean pageBean = new PageBean();
        pageBean.setPage(page);
        pageBean.setRows(rows);

//        log.debug("[com.kainiu.mall.controller.IntegralController.integralQuery]"
//                +"webchatNo"+webchatNo+",page:"+page+",rows"+rows);
//        log.info("[com.kainiu.mall.controller.IntegralController.integralQuery]"
//                +integralService.integralQuery(webchatNo,pageBean));

        return integralService.integralQuery(webchatNo,pageBean);
    }
}
