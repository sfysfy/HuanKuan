package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.util.SPUtils;
import com.repayment.money.R;
import com.repayment.money.common.Constant;

public class SettingActivity extends BaseActivity{
    private LinearLayout mLayoutRegSetActivity;


    @Override
    protected int addRootView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusBarColor));
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setTitleCenter("设置");
        mLayoutRegSetActivity = (LinearLayout) findViewById(R.id.layout_reg_set_activity);

    }

    @Override
    protected void initListener() {
        mLayoutRegSetActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mBaseActivitySelf, LogincAtivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                SPUtils.getInstance(Constant.SP_USER_MSG).remove("phone");
                SPUtils.getInstance(Constant.SP_USER_MSG).remove("pwd");
                SPUtils.getInstance(Constant.SP_USER_MSG).put("isFirst",false);

                startActivity(intent);
            }
        });
    }
}
