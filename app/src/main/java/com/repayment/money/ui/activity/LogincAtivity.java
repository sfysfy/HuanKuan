package com.repayment.money.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;
import com.repayment.money.common.utils.UtilForUserAndPwd;

public class LogincAtivity extends BaseActivity {

    private EditText mEdtUserLoginActivity;
    private EditText mEdtPwdLoginActivity;
    private Button mBtLoginLoginActivity;
    private TextView mTvRegActivityMain;
    private TextView mTvWjActivityMain;


    @Override
    protected int addRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mEdtUserLoginActivity = (EditText) findViewById(R.id.edt_user_login_activity);
        mEdtPwdLoginActivity = (EditText) findViewById(R.id.edt_pwd_login_activity);
        mBtLoginLoginActivity = (Button) findViewById(R.id.bt_login_login_activity);
        mTvRegActivityMain = (TextView) findViewById(R.id.tv_reg_activity_main);
        mTvWjActivityMain = (TextView) findViewById(R.id.tv_wj_activity_main);



    }

    @Override
    protected void initListener() {
        mBtLoginLoginActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userNme=mEdtUserLoginActivity.getText().toString().trim();
                String userPwd=mEdtPwdLoginActivity.getText().toString().trim();

               if (UtilForUserAndPwd.checkNameAndPwd(userNme,userPwd)){
                   Intent intent=new Intent(mBaseActivitySelf,HomeActivity.class);
                   startActivity(intent);
               }

            }
        });

    }
}
