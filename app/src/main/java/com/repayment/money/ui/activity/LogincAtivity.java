package com.repayment.money.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.UtilForUserAndPwd;
import com.repayment.money.db.TableUser;
import com.repayment.money.entity.LoginEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

public class LogincAtivity extends BaseActivityWithNet<LoginEntity> {

    private EditText mEdtUserLoginActivity;
    private EditText mEdtPwdLoginActivity;
    private Button mBtLoginLoginActivity;
    private TextView mTvRegActivityMain;
    private TextView mTvWjActivityMain;
    private LoginEntity mEntityLogin;
    private DbManager mDbManager;


    @Override
    protected int addRootView() {
        return R.layout.activity_main;
    }

  //  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Override
    protected void initNetData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//         //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


          /*  getWindow().setNavigationBarColor(Color.BLUE); //写法一
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));*/
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



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    @Override
    protected void initLocalData() {

        DbManager.DaoConfig daoConfig=new DbManager.DaoConfig()
                .setDbName("HKW")
                .setDbVersion(1);
        mDbManager = x.getDb(daoConfig);

    }

    @Override
    protected void success(LoginEntity entity) {
        mEntityLogin = entity;
        if (entity.getCode()==1) {
            Intent intent=new Intent(mBaseActivitySelf,BoundActivity.class);
            startActivity(intent);
            System.out.println("entity = ======" + entity);
            doSaveUserMsg();
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mBaseActivitySelf, "用户名密码错误", Toast.LENGTH_SHORT).show();
                    mEdtUserLoginActivity.setText("");
                    mEdtUserLoginActivity.setText("");
                }
            });
        }

    }

    private void doSaveUserMsg() {
        TableUser tableUser=new TableUser();
        tableUser.setPhone(mEntityLogin.getResultObj().getName());
        tableUser.setPwd(mEntityLogin.getResultObj().getPassword());

        tableUser.setTimestamp(mEntityLogin.getTimestamp());
        tableUser.setUserNo(mEntityLogin.getResultObj().getUserNo());

        tableUser.setIdcard(mEntityLogin.getResultObj().getIdCard());
        tableUser.setChannel(mEntityLogin.getResultObj().getChannel());
        try {
            mDbManager.saveBindingId(tableUser);
            Log.d("LogincAtivity=", "存储成功");
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void failed(Throwable throwable) {
        Toast.makeText(mBaseActivitySelf, "连接服务器异常", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected String gerUrl() {
        return Constant.LOGIN_URL;
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
                    addParam("mobile",userNme);
                    addParam("password",userPwd);
                    execute();
                }else{
                    Toast.makeText(mBaseActivitySelf, "账号错误", Toast.LENGTH_SHORT).show();
                    mEdtPwdLoginActivity.setText("");
                }
            }
        });
        //注册监听
        mTvRegActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mBaseActivitySelf, RegActivity.class);
                startActivity(intent);
            }
        });

        //忘记密码监听
        mTvWjActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mBaseActivitySelf, ForgetActivity.class);
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
    

    @Override
    protected boolean isNotUseTitle() {
        return true;
    }
}
