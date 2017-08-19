package com.repayment.money.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

public class BoundActivity extends BaseActivity {

    private EditText mEdtNameActivityBound;
    private EditText mEdtIdCardBoundActivity;
    private LinearLayout mLayoutIdCardActivityBound;
    private EditText mEdtNumCardBoundActivity;
    private EditText mEdtNameBankBoundActivity;
    private EditText mEdtNumPhoneBoundActivity;
    private Button mBtDoActivityBound;

    @Override
    protected int addRootView() {
        return R.layout.activity_bound;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

       /* if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusBarColor));
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/




        setTitleCenter("银行卡认证");



        mBtDoActivityBound = (Button) findViewById(R.id.bt_do_activity_bound);

        mEdtIdCardBoundActivity = (EditText) findViewById(R.id.edt_id_card_bound_activity);
        mLayoutIdCardActivityBound = (LinearLayout) findViewById(R.id.layout_id_card_activity_bound);
        mEdtNameActivityBound = (EditText) findViewById(R.id.edt_name_activity_bound);
        mEdtNumCardBoundActivity = (EditText) findViewById(R.id.edt_num_card_bound_activity);
        mEdtNameBankBoundActivity = (EditText) findViewById(R.id.edt_name_bank_bound_activity);
        mEdtNumPhoneBoundActivity = (EditText) findViewById(R.id.edt_num_phone_bound_activity);
        //mLayoutIdCardActivityBound.setVisibility(View.GONE);




    }

    @Override
    protected void initListener() {
        mBtDoActivityBound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
