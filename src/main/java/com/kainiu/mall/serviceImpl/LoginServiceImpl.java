package com.kainiu.mall.serviceImpl;

import com.kainiu.mall.entity.Customer;
import com.kainiu.mall.mapper.LoginMapper;
import com.kainiu.mall.service.LoginService;
import com.kainiu.mall.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信小程序-登录
 * 2019-12-8
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 通过openID判断该用户是否存在
     * webchatNo:openID
     */
    public Customer loginUserQuery(String webchatNo){
        Customer cust = null;
//        log.debug("[com.kainiu.mall.serviceImpl.LoginServiceImpl.loginUserQuery]"
//                +"webchatNo:"+webchatNo);
//
//        log.debug("[com.kainiu.mall.serviceImpl.LoginServiceImpl.loginUserQuery]"
//                +loginMapper.loginUserQuery(webchatNo));
        if(null != webchatNo && !webchatNo.isEmpty ()) {
            cust = loginMapper.loginUserQuery (webchatNo);
        }
        return cust;
    }

    /**
     * 新增用户
     * user:用户实体类
     */
    public ResultData newUsers(Customer customer){


//        log.debug("[com.kainiu.mall.serviceImpl.LoginServiceImpl.newUsers]"
//                +"user:"+customer);

        //新增用户
        if(null != customer.getWebchatNo ()) {
            loginMapper.newUsers (customer);
        }
        return ResultData.ok();
    }

    /**
     * 修改用户
     * user：用户实体类
     */
    public ResultData updateUsers(Customer customer){
//        log.debug("[com.kainiu.mall.serviceImpl.LoginServiceImpl.updateUsers]"
//                +"user:"+customer);

        //修改用户
        if(null != customer.getWebchatNo ()) {
            loginMapper.updateUsers(customer);
        }
        return ResultData.ok();
    }

    /**
     * 用户协议
     * id 用户协议
     */
    public ResultData userAgreement(Integer id){

//        log.debug("[com.kainiu.mall.serviceImpl.LoginServiceImpl.userAgreement]"
//                +"id:"+id);
//
//        log.debug("[com.kainiu.mall.serviceImpl.LoginServiceImpl.userAgreement]"
//                +loginMapper.userAgreement(id));

         return ResultData.ok(loginMapper.userAgreement(id));
    }
}
