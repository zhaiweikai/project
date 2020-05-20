package com.kainiu.mall.util;

import java.text.DecimalFormat;

//根据距离增加单位
public class GetDistanceStr {

    public static String getDistanceStr(double  seller){
        DecimalFormat df  =new DecimalFormat("#.0");
        String detail = "";

        double tempDis = seller / 1000;
        detail = String.format("%.1f", tempDis);
        if(Double.parseDouble(detail) >= 1){
            detail = detail + "Km";
            return detail;
        }else{
            return (int) Math.ceil(Double.parseDouble(detail) * 1000) + "m";
        }
    }

}
