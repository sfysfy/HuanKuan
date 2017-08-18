package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//         //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);



            getWindow().setNavigationBarColor(Color.BLUE); //写法一
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            *//*getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*//*
//
//            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.BLACK); //写法一
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }*/



        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/


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
        //登录监听
        mBtLoginLoginActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userNme = mEdtUserLoginActivity.getText().toString().trim();
                String userPwd = mEdtPwdLoginActivity.getText().toString().trim();

                if (UtilForUserAndPwd.checkNameAndPwd(userNme, userPwd)) {
                    Intent intent = new Intent(mBaseActivitySelf, HomeActivity.class);
                    startActivity(intent);
                }

            }
        });
        //注册监听
        mTvRegActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mBaseActivitySelf, HomeActivity.class);
                startActivity(intent);
            }
        });

        //忘记密码监听
        mTvWjActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mBaseActivitySelf, HomeActivity.class);
                startActivity(intent);
            }
        });

        //对密码输入框类型的设置
        mEdtPwdLoginActivity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char[] chars = s.toString().toCharArray();
                for (int i = 0; i < s.toString().length(); i++) {
                    if (!s.toString().matches("[a-zA-Z0-9]*")) {
                        Toast.makeText(mBaseActivitySelf, "输入有误,仅限数字字母", Toast.LENGTH_SHORT).show();
//                        mEdtPwdLoginActivity.setText(s.toString().substring(0,s.toString().length()-1));
                        mEdtPwdLoginActivity.setText("");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
