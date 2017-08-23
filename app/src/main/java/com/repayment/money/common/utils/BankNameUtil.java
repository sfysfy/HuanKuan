package com.repayment.money.common.utils;

/**
 * Created by 11250 on 2017/8/23.
 */

public class BankNameUtil {
    //建设银行,3443----->建设银行(3443)
    public static String bankNameFormat(String bankName,String bankCard){
        String s = UtilForBankCardNum.fourCardNumkh(bankCard);
        return bankName+"  "+s;
    }
}
