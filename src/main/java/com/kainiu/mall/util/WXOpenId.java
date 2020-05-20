package com.kainiu.mall.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//获取小程序opendID
public class WXOpenId {




    public static JSONObject getSessionKeyOrOpenId(String code){
        //微信端登录code
        String wxCode = code;
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<>(  );
        requestUrlParam.put( "appid","wxa0ef59f2731d264e");//小程序appId
        requestUrlParam.put( "secret","17fa519d0df85bf3f9e485a155b4ba29" );//小程序秘钥
        requestUrlParam.put( "js_code",wxCode );//小程序端返回的code
        requestUrlParam.put( "grant_type","authorization_code" );//默认参数

        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject( UrlUtil.sendPost( requestUrl,requestUrlParam ));
        return jsonObject;
    }

}
