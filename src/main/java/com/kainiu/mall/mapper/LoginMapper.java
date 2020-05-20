package com.kainiu.mall.mapper;

import com.kainiu.mall.entity.Customer;
import com.kainiu.mall.entity.FinProtocol;
import com.kainiu.mall.util.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 微信小程序-用户登录
 * 2019-12-8
 */
@Repository
public interface LoginMapper {

    /**
     * 通过openID判断该用户是否存在
     * webchatNo:openID
     */
    public Customer loginUserQuery(@Param("webchatNo") String webchatNo);

    /**
     * 新增用户
     * user:用户实体类
     */
    public void newUsers(@Param("customer") Customer customer);

    /**
     * 修改用户
     * user：用户实体类
     */
    public void updateUsers(@Param("customer") Customer customer);

    /**
     * 用户协议
     * id 用户协议
     */
    public FinProtocol userAgreement(@Param("id")Integer id);
}
