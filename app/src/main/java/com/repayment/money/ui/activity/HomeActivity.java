package com.repayment.money.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

public class HomeActivity extends BaseActivity {




    @Override
    protected int addRootView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitleCenter("还款王");
        setViewTitleRight("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    protected void initListener() {


    }
}
