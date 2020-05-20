package com.kainiu.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.kainiu.mall.entity.Customer;
import com.kainiu.mall.service.LoginService;
import com.kainiu.mall.util.ResultData;
import com.kainiu.mall.util.WXOpenId;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序-登录页
 * 2019-12-7
 */
@RestController
@RequestMapping("/wx")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(IntegralController.class);

    @Resource
    private LoginService loginService;

    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> doLogin(Model model,
                                      @RequestParam(value = "code",required = false) String code,
                                      @RequestParam(value = "rawData",required = false) String rawData,
                                      @RequestParam(value = "signature",required = false) String signature,
                                      @RequestParam(value = "encrypteData",required = false) String encrypteData,
                                      @RequestParam(value = "iv",required = false) String iv){
        log.info( "Start get SessionKey" );


        Map<String,Object> map = new HashMap<>(  );
        log.debug("用户非敏感信息"+rawData);

        JSONObject rawDataJson = JSON.parseObject( rawData );

        log.debug("签名"+signature);
        JSONObject SessionKeyOpenId = WXOpenId.getSessionKeyOrOpenId( code );
        log.info("post请求获取的SessionAndopenId="+SessionKeyOpenId);

        String openid = SessionKeyOpenId.getString("openid" );

        String sessionKey = SessionKeyOpenId.getString( "session_key" );

        log.info("openid="+openid+",session_key="+sessionKey);

        //判断openid是否存在
        Customer customer = loginService.loginUserQuery(openid);
        if(customer==null){
            //不存在新增
            Customer customers= new Customer();
            //设置微信openid
            customers.setWebchatNo(openid);
            loginService.newUsers(customers);
        }else {
            //已存在
            log.info( "用户openid已存在,不需要插入" );
        }

        //把新的sessionKey和oppenid返回给小程序
        map.put( "skey",sessionKey );

        map.put( "result","0" );

        JSONObject userInfo = getUserInfo( encrypteData, sessionKey, iv );
        log.debug("根据解密算法获取的userInfo="+userInfo);
        map.put( "userInfo",userInfo );

        return map;
    }

    //解密获取用户id
    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init( Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidParameterSpecException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    //获取微信手机号
    /**
     * @author kemi
     * 微信小程序绑定手机号  不发送验证码
     * @param encryptedData
     * @param iv
     * @param sessionKey
     * @return
     */
    @ResponseBody
    @RequestMapping("/binding/mobilePhone")
    public Map<String,Object> doLogin(Model model,
                                      @RequestParam(value = "encryptedData",required = false) String encryptedData,
                                      @RequestParam(value = "iv",required = false) String iv,
                                      @RequestParam(value = "sessionKey",required = false) String sessionKey,
                                      @RequestParam(value = "openId",required = false) String openId){

        //手机号解密
        JSONObject obj=getPhoneNumber(sessionKey,encryptedData,iv);
        String phone=obj.get("phoneNumber").toString();
//        log.debug("微信手机号："+phone);
        //修改用户手机号
        Customer customer =new Customer();
        customer.setWebchatNo(openId);
        customer.setObilePhone(phone);
        loginService.updateUsers(customer);

        //返回参数
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("phone",phone);

        return jsonObject;
    }

    /**
     * 解密手机号
     * @author kemi
     * @param session_key
     * @param encryptedData
     * @param iv
     * @return
     */
    public JSONObject getPhoneNumber(String session_key,String encryptedData,String iv ){
        byte[] dataByte = Base64.decode(encryptedData);
        byte[] keyByte = Base64.decode(session_key);
        byte[] ivByte = Base64.decode(iv);
        try {
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    //用户登录协议
    @PostMapping("/userAgreement")
    @ResponseBody
    public ResultData userAgreement(@RequestBody JSONObject jsonParam) {
        //获取查询条件
        Integer id =(Integer) jsonParam.get("id");

//        log.debug("[com.kainiu.mall.controller.LoginController.userAgreement]"
//                +"id:"+id);
//        log.info("[com.kainiu.mall.controller.LoginController.userAgreement]"
//                +loginService.userAgreement(id));

        return loginService.userAgreement(id);
    }

}
