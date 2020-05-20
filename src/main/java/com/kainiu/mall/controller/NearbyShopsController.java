package com.kainiu.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.service.IntegralService;
import com.kainiu.mall.service.NearbyShopsService;
import com.kainiu.mall.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信小程序-附近商家
 * 2019-12-6
 */
@RestController
@RequestMapping("/wx/nearbyShops")
public class NearbyShopsController {
    private static Logger log = LoggerFactory.getLogger(NearbyShopsController.class);

    @Resource
    private NearbyShopsService nearbyShopsService;

    /**
     * 附近商家列表查询
     * lon:经度
     * lat:纬度
     * page：页数
     * rows：显示行数
     */
    @PostMapping("/nearbyShopsList")
    @ResponseBody
    public ResultData nearbyShopsListQuery(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        Double lon =(Double) jsonParam.get("lon");
        Double lat =(Double) jsonParam.get("lat");
        Integer page =(Integer) jsonParam.get("page");
        Integer rows =(Integer) jsonParam.get("rows");

        //设置查询开始页数、行数
        PageBean pageBean = new PageBean();
        pageBean.setPage(page);
        pageBean.setRows(rows);

//        log.debug("[com.kainiu.mall.controller.NearbyShopsController.nearbyShopsListQuery]"
//                +"lon:"+lon+",lat"+lat+",page:"+page+",rows"+rows);
//        log.info("[com.kainiu.mall.controller.NearbyShopsController.nearbyShopsListQuery]"
//                +nearbyShopsService.NearbyShopsListQuery(lon,lat,pageBean));

        return nearbyShopsService.NearbyShopsListQuery(lon,lat,pageBean);
    }

    /**
     * 附近商家详情查询
     * locationId：位置id
     */
    @PostMapping("/nearbyShopsQuery")
    @ResponseBody
    public ResultData nearbyShopsQuery(@RequestBody JSONObject jsonParam){
        //获取查询条件
        String locationId =(String) jsonParam.get("locationId");

//        log.debug("[com.kainiu.mall.controller.NearbyShopsController.nearbyShopsQuery]"
//                +"locationId:"+locationId);
//        log.info("[com.kainiu.mall.controller.NearbyShopsController.nearbyShopsQuery]"
//                +nearbyShopsService.nearbyShopsQuery(locationId));

        return nearbyShopsService.nearbyShopsQuery(locationId);

    }
}
