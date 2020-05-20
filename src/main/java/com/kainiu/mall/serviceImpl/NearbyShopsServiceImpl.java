package com.kainiu.mall.serviceImpl;

import com.kainiu.mall.controller.MallController;
import com.kainiu.mall.entity.Merchants;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.mapper.NearbyShopsMapper;
import com.kainiu.mall.service.NearbyShopsService;
import com.kainiu.mall.util.GetDistanceStr;
import com.kainiu.mall.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信小程序-附近商家
 * 2019-11-29
 */
@Service
public class NearbyShopsServiceImpl implements NearbyShopsService {

    private static Logger log = LoggerFactory.getLogger(MallController.class);
    @Autowired
    private NearbyShopsMapper nearbyShopsMapper;

    /**
     * 获取附近商家列表
     * lon:经度
     * lat:纬度
     * pageBean:列表分页
     */
    public ResultData NearbyShopsListQuery(double lon, double lat, PageBean pageBean){

//        log.debug("[com.kainiu.mall.serviceImpl.NearbyShopsServiceImpl.NearbyShopsListQuery]"
//                +"lon:"+lon+",lat:"+lat+",pageBean"+pageBean);
//        log.debug("[com.kainiu.mall.serviceImpl.NearbyShopsServiceImpl.NearbyShopsListQuery]"
//                +nearbyShopsMapper.nearbyShopsList(lon,lat,pageBean));
        
        //获取附近商家列表
        List<Merchants> MerchantsList=nearbyShopsMapper.nearbyShopsList(lon,lat,pageBean);

        /**
         * 把距离添加单位
         * 判断是否为空
         */
        if(MerchantsList.size()!=0){

            for (int i = 0; i < MerchantsList.size(); i++) {

                String distanceUnit =GetDistanceStr.getDistanceStr(
                        MerchantsList.get(i).getDistance());

                MerchantsList.get(i).setDistanceUnit(distanceUnit);
            }
        }
        
        return ResultData.ok(MerchantsList);
    }

    /**
     * 附近商家详情查询
     * locationId：位置id
     */
    public ResultData nearbyShopsQuery(String locationId){
//        log.debug("[com.kainiu.mall.serviceImpl.NearbyShopsServiceImpl.nearbyShopsQuery]"
//                +"locationId:"+locationId);
//        log.debug("[com.kainiu.mall.serviceImpl.NearbyShopsServiceImpl.nearbyShopsQuery]"
//                +nearbyShopsMapper.nearbyShopsQuery(locationId));
        return ResultData.ok(nearbyShopsMapper.nearbyShopsQuery(locationId));
    }
}
