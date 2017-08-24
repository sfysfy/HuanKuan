package com.repayment.money.common.utils;

import android.util.Log;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by 11250 on 2017/8/23.
 */

public class UtilForItemBill {
    public static String moneyZSFormat(float money){
        String smoney=money+"";

        String[] split = smoney.split("\\.");
        String zs = null;
        if (split.length==2){
            zs= split[0];
            return zs;
        }
        return smoney;
    }
    public static String moneyXSFormat(float money){
        String smoney=money+"";
        String[] split = smoney.split("\\.");
        if (!split[1].equals("0")) {
            return split[1];
        }
        return "00";
    }
    public static String getBillType(int type){
        if(type==0){
            return "房贷";
        }

        return "";
    }
}
