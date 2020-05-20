package com.kainiu.mall.mapper;

import com.kainiu.mall.entity.Merchants;
import com.kainiu.mall.entity.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 微信小程序-附近商家
 * 2019-12-6
 */
@Repository
public interface NearbyShopsMapper {

    /**
     * 附近商家列表查询
     * lon:经度
     * lat:纬度
     * pageBean:列表分页
     */
    public List<Merchants> nearbyShopsList(@Param("lon") Double lon, @Param("lat") Double lat
            , @Param("pageBean") PageBean pageBean);

    /**
     * 附近商家详情查询
     * locationId：位置id
     */
    public Merchants nearbyShopsQuery(@Param("locationId") String locationId);

}
