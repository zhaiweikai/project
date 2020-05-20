package com.kainiu.mall.service;

import com.kainiu.mall.entity.Merchants;
import com.kainiu.mall.entity.PageBean;
import com.kainiu.mall.util.ResultData;
import org.apache.ibatis.annotations.Param;

/**
 * 微信小程序-附近商家
 * 2019-12-6
 */
public interface NearbyShopsService {

    /**
     * 获取附近商家列表
     * lon:经度
     * lat:纬度
     * pageBean:列表分页
     */
    public ResultData NearbyShopsListQuery(double lon, double lat, PageBean pageBean);

    /**
     * 附近商家详情查询
     * locationId：位置id
     */
    public ResultData nearbyShopsQuery(String locationId);
}
