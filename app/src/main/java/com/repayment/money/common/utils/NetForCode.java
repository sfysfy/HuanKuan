package com.repayment.money.common.utils;

import android.util.Log;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.entity.SendCodeEntity;

import static com.repayment.money.common.Constant.BASE_URL_CODE;

/**
 * Created by 11250 on 2017/8/19.
 */

public class NetForCode {
    public  NetForJson mNetForJson;

    public NetForCode() {

        mNetForJson = new NetForJson(BASE_URL_CODE, new NetOverListener<SendCodeEntity>() {
            @Override
            public void success(SendCodeEntity sendCodeEntity) {

            }

            @Override
            public void failed(Throwable throwable) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }
        });
        mNetForJson.setClassEntity(SendCodeEntity.class);
    }

    public void sendRegCode(String phone) {
        //http://101.200.128.107:10028/repayment/user/sendCode?mobile=15731660437&flag=0

        mNetForJson.addParam("mobile", phone);
        mNetForJson.addParam("flag", 0);
        mNetForJson.execute();
    }
    public void sendForgetCode(String phone) {
        //http://101.200.128.107:10028/repayment/user/sendCode?mobile=15731660437&flag=0

        mNetForJson.addParam("mobile", phone);
        mNetForJson.addParam("flag", 1);
        mNetForJson.execute();
    }

}
