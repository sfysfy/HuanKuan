package com.repayment.money.common.utils;

import android.util.EventLog;
import android.util.Log;
import android.widget.Toast;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.entity.BankCardEntity;
import com.repayment.money.entity.SendCodeEntity;
import com.repayment.money.ui.activity.NewBillActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.repayment.money.common.Constant.BASE_URL_CODE;

/**
 * Created by 11250 on 2017/8/19.
 */

public class NetForBankCard {
    public NetForJson mNetForJson;

    public  void yzBankCard(String card) {

        mNetForJson = new NetForJson("http://101.200.128.107:10028/repayment/bank/bankCardBin", new NetOverListener<BankCardEntity>() {

            @Override
            public void success(BankCardEntity bankCardEntity) {

            }

            @Override
            public void failed(Throwable throwable) {
                EventBus.getDefault().post(3);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }
        });
    }


}
