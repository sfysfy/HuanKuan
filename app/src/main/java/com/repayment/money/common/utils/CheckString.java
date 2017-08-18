package com.repayment.money.common.utils;

import com.example.mylibrary.util.PhoneUtils;

/**
 * Created by 11250 on 2017/8/17.
 */

public class CheckString {
    public static boolean isTruePhone(String phoneNum) {
        return PhoneUtils.isRightNum(phoneNum);
    }

    public static boolean isTruePwd(String pwd) {
        if (pwd.length()<6||pwd.length()>16) {
            return false;
        }
        char[] cs=pwd.toCharArray();
        for (char c : cs) {
            if(!(c>='0'&&c<='9'||c>='a'&&c<='z'||c>='A'&&c<='Z')){
                return false;
            }
        }
        return true;
    }

    public static boolean isTrueVal(String val) {
        if("".equals(val)){
            return false;
        }
        char[] cs=val.toCharArray();
        for(char c:cs){
            if(!(c>='0' && c<='9')){
                return false;
            }
        }
        if(val.length()<6){
            return false;
        }
        return true;
    }

}
