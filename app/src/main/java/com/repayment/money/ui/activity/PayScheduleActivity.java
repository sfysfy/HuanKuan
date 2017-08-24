package com.repayment.money.ui.activity;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.entity.BillDetailEntity;

public class PayScheduleActivity extends BaseActivityWithNet<BillDetailEntity> {


    @Override
    protected void initNetData() {

    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected void success(BillDetailEntity entity) {

    }

    @Override
    protected void failed(Throwable throwable) {

    }

    @Override
    protected String gerUrl() {
//        http://101.200.128.107:10028/repayment/order/findHuankuan?orderNo=2017082316215303710001
        //还款状态1-已还款,0-未还款,3-还款处理中,4-还款失败
        return Constant.BILL_DETAIL_URL;
    }

    @Override
    protected int addRootView() {
        return R.layout.activity_pay_schedule;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }
}
