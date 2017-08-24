package com.repayment.money.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.base.BaseFragment;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.ui.activity.CardControlActivity;
import com.repayment.money.ui.activity.LogincAtivity;
import com.repayment.money.ui.activity.SettingActivity;


public class UserCenterFragment extends BaseFragment {
    private LinearLayout mItemBankcardUserFragment;
    private LinearLayout mItemSettingUserFragment;
    private TextView mTvUsernameFragment;



    @Override
    protected int addRootView() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mItemBankcardUserFragment = (LinearLayout) findViewById(R.id.item_bankcard_user_fragment);
        mItemSettingUserFragment = (LinearLayout) findViewById(R.id.item_setting_user_fragment);
        mTvUsernameFragment = (TextView) findViewById(R.id.tv_username_fragment);
        if (Constant.getTableuser()!=null) {
            mTvUsernameFragment.setText(Constant.getTableuser().getPhone());
        }else{
            mTvUsernameFragment.setText("登录/注册");
            mTvUsernameFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mBaseActivitySelf,LogincAtivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    protected void initListener() {
        mItemBankcardUserFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mBaseActivitySelf,CardControlActivity.class);
                startActivity(intent);
            }
        });
        mItemSettingUserFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mBaseActivitySelf, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected boolean isNotUseTitle() {
        return true;
    }
}
