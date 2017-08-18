package com.repayment.money.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

public class HomeActivity extends BaseActivity {
    private TextView mTvShowActivityHome;




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
        setViewTitleRight("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvShowActivityHome = (TextView) findViewById(R.id.tv_show_activity_home);
        CharSequence str="暂无账单\n快来添加您的账单吧";

        mTvShowActivityHome.setText(str);

    }


    protected void initListener() {


    }
}
