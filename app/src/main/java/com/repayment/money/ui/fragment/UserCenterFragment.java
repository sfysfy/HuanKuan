package com.repayment.money.ui.fragment;

import android.content.Context;
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

public class UserCenterFragment extends BaseFragment {
    private LinearLayout mItemBankcardUserFragment;
    private LinearLayout mItemBillUserFragment;
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
        mItemBillUserFragment = (LinearLayout) findViewById(R.id.item_bill_user_fragment);
        mItemSettingUserFragment = (LinearLayout) findViewById(R.id.item_setting_user_fragment);

    }

    @Override
    protected void initListener() {
        mItemBankcardUserFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mBaseActivitySelf, "我是银行卡管理", Toast.LENGTH_SHORT).show();
            }
        });
        mItemBillUserFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mBaseActivitySelf, "我是账单管理", Toast.LENGTH_SHORT).show();
            }
        });
        mItemSettingUserFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mBaseActivitySelf, "我是设置", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected boolean isNotUseTitle() {
        return true;
    }
}
