package com.repayment.money.common.utils;

import com.example.mylibrary.util.PhoneUtils;

/**
 * Created by 马彦虎 on 2017/8/17.
 */

public class UtilForUserAndPwd {
    public static boolean checkNameAndPwd(String name,String pwd){
        if (name.length()<6||pwd.length()<6){
            return false;
        }else {
            if (PhoneUtils.isRightNum(name)) {
                return true;
            }else {
                return false;
            }
        }


    }
}
