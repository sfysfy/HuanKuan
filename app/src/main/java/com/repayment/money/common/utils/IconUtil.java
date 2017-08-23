package com.repayment.money.common.utils;


import com.repayment.money.R;

public class IconUtil {
    public static int getIcon(String bangName){

        if ("中国农业银行".equals(bangName)){
            return R.drawable.nonghang;
        }
        if ("交通银行".equals(bangName)){
            return R.mipmap.ic_launcher;
        }
        if ("中国工商银行".equals(bangName)){
            return R.drawable.gonghang;
        }
        if ("中国邮政储蓄银行".equals(bangName)){
            return R.drawable.youzheng;
        }
        if ("上海浦发银行".equals(bangName)){
            return R.drawable.shanghaipudong;
        }
        if ("平安银行".equals(bangName)){
            return R.drawable.pingan;
        }

        if ("广东发展银行".equals(bangName)){
            return R.drawable.pufa;
        }
        if ("招商银行".equals(bangName)){
            return R.mipmap.ic_launcher;
        }
        if ("中国银行".equals(bangName)){
            return R.drawable.zhonghang;
        }
        if ("光大银行".equals(bangName)){
            return R.drawable.guangda;
        }
        if ("兴业银行".equals(bangName)){
            return R.drawable.xingye;
        }
        if ("中信银行".equals(bangName)){
            return R.mipmap.ic_launcher;
        }



        if ("华夏银行".equals(bangName)){
            return R.drawable.huaxia;
        }
        if ("杭州银行".equals(bangName)){
            return R.mipmap.ic_launcher;
        }
        if ("北京银行".equals(bangName)){
            return R.mipmap.ic_launcher;
        }
        if ("浙商银行".equals(bangName)){
            return R.mipmap.ic_launcher;
        }
        if ("上海银行".equals(bangName)){
            return R.drawable.shanghaiyinhang;
        }
        if ("宁波银行".equals(bangName)){
            return R.mipmap.ic_launcher;
        }
        return R.drawable.bankcard;
    }
}
