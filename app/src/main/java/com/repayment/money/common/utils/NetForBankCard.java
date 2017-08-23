package com.repayment.money.common.utils;

import android.util.Log;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.entity.BankCardEntity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 11250 on 2017/8/19.
 */

public class NetForBankCard {
    private NetForJson mNetForJson;

    public  void yzBankCard(String card) {

        mNetForJson = new NetForJson("http://101.200.128.107:10028/repayment/bank/bankCardBin", new NetOverListener<BankCardEntity>() {
            @Override
            public void success(BankCardEntity bankCardEntity) {
                Log.d("NetForBankCard", "bankCardEntity:" + bankCardEntity);
                EventBus.getDefault().post(bankCardEntity.getResultObj().getCard_type());
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

        mNetForJson.addParam("bankCard",card);
        mNetForJson.execute();
    }


}
