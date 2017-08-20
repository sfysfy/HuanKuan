package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

public class HomeActivity extends BaseActivity {
    private Button mBtAddMsgActivityMain;





    @Override
    protected int addRootView() {
        return R.layout.activity_home;
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
        setTitleCenter("还款王");
        setViewTitleRight("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtAddMsgActivityMain = (Button) findViewById(R.id.bt_add_msg_activity_main);



    }


    protected void initListener() {
        mBtAddMsgActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mBaseActivitySelf,NewBillActivity.class);
                startActivity(intent);
            }
        });

    }
}
