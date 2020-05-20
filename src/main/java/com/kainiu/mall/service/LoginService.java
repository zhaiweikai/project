package com.kainiu.mall.service;

import com.kainiu.mall.entity.Customer;
import com.kainiu.mall.util.ResultData;

//微信小程序- 登录
//2019-12-8
public interface LoginService {

    /**
     * 通过openID判断该用户是否存在
     * webchatNo:openID
     */
    public Customer loginUserQuery(String webchatNo);

    /**
     * 新增用户
     * user:用户实体类
     */
    public ResultData newUsers(Customer customer);

    /**
     * 修改用户
     * user：用户实体类
     */
    public ResultData updateUsers(Customer customer);

    /**
     * 用户协议
     * id 用户协议
     */
    public ResultData userAgreement(Integer id);
}
