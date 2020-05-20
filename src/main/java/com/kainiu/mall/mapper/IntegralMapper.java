package com.kainiu.mall.mapper;

import com.kainiu.mall.entity.ConsumeDetail;
import com.kainiu.mall.entity.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 微信小程序-积分消费记录
 * 2019-12-5
 */
@Repository
public interface IntegralMapper {
    /**
     * 账户积分余额
     * webchatNo：微信号
     */
    public Integer integralBalance(@Param("webchatNo") String webchatNo);

    /**
     * 积分消费记录
     * webchatNo：微信号
     */
    public List<ConsumeDetail> expenseCalendar(@Param("webchatNo") String webchatNo
            ,@Param("pageBean") PageBean pageBean);

}
