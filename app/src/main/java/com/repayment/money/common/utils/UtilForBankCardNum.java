package com.repayment.money.common.utils;

/**
 * Created by 11250 on 2017/8/22.
 */

public class UtilForBankCardNum {
    public static String fourCardNum(String num){
        String substring = num.substring(num.length() - 4, num.length());
        return substring;
    }
    //带括号
    public static String fourCardNumkh(String num){
        String substring = num.substring(num.length() - 4, num.length());
        String card="("+substring+")";
        return card;
    }
}
