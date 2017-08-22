package com.repayment.money.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mylibrary.base.BaseFragment;
import com.repayment.money.R;
import com.repayment.money.ui.activity.CardControlActivity;
import com.repayment.money.ui.activity.SettingActivity;

public class UserCenterFragment extends BaseFragment {
    private LinearLayout mItemBankcardUserFragment;
    private LinearLayout mItemSettingUserFragment;


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

    }

    @Override
    protected void initListener() {
        mItemBankcardUserFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mBaseActivitySelf, CardControlActivity.class);
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
