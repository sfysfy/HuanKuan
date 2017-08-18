package com.repayment.money;

import com.example.mylibrary.base.BaseApplication;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by 马彦虎 on 2017/8/17.
 */

public class RepayMoneyApplication extends BaseApplication {

    @Override
    protected int initTitleBarId() {
        return R.layout.title_repay;
    }

    @Override
    protected void initOthets() {
        CrashReport.initCrashReport(getApplicationContext(), "0a6fe297b6", true);
    }

    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(context);
        Multidex.install(this);
    }*/
}
