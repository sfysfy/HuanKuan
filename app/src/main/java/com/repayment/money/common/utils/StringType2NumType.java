package com.repayment.money.common.utils;

/**
 * Created by 11250 on 2017/8/28.
 */

public class StringType2NumType {
    public static String stringType2NumType(String type){
        if ("房贷".equals(type)){
            return "0";
        }if ("车贷".equals(type)){
            return "0";
        }if ("现金贷".equals(type)){
            return "0";
        }if ("花呗".equals(type)){
            return "0";
        }if ("京东白条".equals(type)){
            return "0";
        }
        return "";
    }
}
