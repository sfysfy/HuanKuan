package com.repayment.money.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

public class CardControlActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtAddCardActivity;
    private ListView mLvCardMsg;
    private LinearLayout mLayoutHideCardActivity;
    private LinearLayout mLayoutAddCardActivity;





    @Override
    protected int addRootView() {
        return R.layout.activity_card_control;
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

        mBtAddCardActivity = (Button) findViewById(R.id.bt_add_card_activity);
        mLvCardMsg = (ListView) findViewById(R.id.lv_card_msg);
        mLayoutHideCardActivity = (LinearLayout) findViewById(R.id.layout_hide_card_activity);
        mLayoutAddCardActivity = (LinearLayout) findViewById(R.id.layout_add_card_activity);



    }

    @Override
    protected void initListener() {
        mBtAddCardActivity.setOnClickListener(this);

    }

    @Override
    protected boolean isNotUseTitle() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_card_activity:
                mLayoutHideCardActivity.setVisibility(View.GONE);
        }
    }
}
