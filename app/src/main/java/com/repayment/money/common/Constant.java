package com.repayment.money.common;
import com.repayment.money.db.TableUser;
import com.repayment.money.ui.activity.LogincAtivity;
import org.xutils.ex.DbException;

public class Constant {
    public static final String SP_LOGIN_COUNT_DOWN="loginCD";
    public static final String SP_LOGIN_ACCOUNT="loginUser";

    public static final String BASE_URL="http://101.200.128.107:10028/repayment/user/";

    public static final String LOGIN_URL="http://101.200.128.107:10028/repayment/user/login";

    public static final String SP_USER_MSG="USER";

    public static  String PHONE_NUM_USER_NOW="";
    public static  String USERNO_NUM_USER_NOW="";

    //http://open.lianlianpay.com/

    public static final String BASE_URL_CODE=BASE_URL+"sendCode";
    public static final String BASE_URL_RESETPASS=BASE_URL+"resetPass";
    public static final String BASE_URL_CHECKBANKCARD="http://101.200.128.107:10028/repayment/bank/checkBankCard";

//    http://101.200.128.107:10028/repayment/order/addOrder?userNo=2017081911412784240032&orderType=0&periodsType=M&periods=12&monthMoney=200&h
    public static final String BASE_URL_NEWBILL="http://101.200.128.107:10028/repayment/order/addOrder";

    public static final String BILL_DETAIL_URL="http://101.200.128.107:10028/repayment/order/findHuankuan";


    public static TableUser getTableuser(){
        TableUser tableUser = null;
        try {
            tableUser= LogincAtivity.mDbManager.selector(TableUser.class).where("phone", "=",PHONE_NUM_USER_NOW).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return tableUser;
    }

    public static final String CARD_BIND_LIST="http://101.200.128.107:10028/repayment/bank/findBankList";

    public static String MRFKBankCard="";

}