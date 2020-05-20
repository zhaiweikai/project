package com.kainiu.mall.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
//返回参数设置
@Data
public class ResultData {
    private Boolean success;
    private String info;
    private Object data;

    public ResultData() {
    }

    public ResultData(Boolean success, String info, Object data) {
        this.success = success;
        this.info = info;
        this.data = data;
    }

    public static ResultData ok() {
        return new ResultData(true, null, null);
    }

    public static ResultData ok(String info) {
        return new ResultData(true, info, null);
    }

    public static ResultData ok( Object data) {
        return new ResultData(true, null, data);
    }

    public static ResultData ok(JSONObject data) {
        return new ResultData(true, null, data);
    }

    public static ResultData ok(String info, Object data) {
        return new ResultData(true, info, data);
    }

    public static ResultData error() {
        return new ResultData(false, null, null);
    }

    public static ResultData error(String info) {
        return new ResultData(false, info, null);
    }

    public static ResultData error(String info, Object data) {
        return new ResultData(false, info, data);
    }

}
