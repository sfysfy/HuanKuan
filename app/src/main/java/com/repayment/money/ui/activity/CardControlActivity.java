package com.repayment.money.ui.activity;

import android.widget.ListView;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

public class CardControlActivity extends BaseActivity {
    private ListView mLvCardMsg;


    @Override
    protected int addRootView() {
        return R.layout.activity_card_control;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mLvCardMsg = (ListView) findViewById(R.id.lv_card_msg);
        setTitleCenter("银行卡管理");
//        setViewTitleRight("");



    }

    @Override
    protected void initListener() {

    }
}
