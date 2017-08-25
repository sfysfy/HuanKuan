package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

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
                /*Intent intent=new Intent(mBaseActivitySelf,LogincAtivity.class);
                startActivity(intent);
                ActivityControl.killAll();
                mBaseActivitySelf.finish();*/

                Intent intent = new Intent(mBaseActivitySelf, LogincAtivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Toast.makeText(mBaseActivitySelf, "执行了finifish", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
