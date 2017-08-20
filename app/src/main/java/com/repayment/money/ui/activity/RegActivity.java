package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.base.BaseActivityWithNet;
import com.example.mylibrary.util.SPUtils;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.CheckString;
import com.repayment.money.common.utils.NetForCode;
import com.repayment.money.entity.RegEntity;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.password;
import static com.repayment.money.common.Constant.BASE_URL;


public class RegActivity extends BaseActivityWithNet<RegEntity> implements View.OnClickListener {

    private ImageView mImageView;
    private EditText mEdtRegnameReg;
    private EditText mEdtValidateReg;
    private Button mBtValidateReg;
    private EditText mEdtRegpwdReg;
    private Button mBtRegReg;
    private TextView mTvLoginReg;

    private static final int CODE_GET_OK=110;//获取验证码成功
    private static final int CODE_YZ_OK=120;//验证码验证成功
    private static final int CODE_ERROR=130;//验证动作失败
    private static final int COUNT_DONW=140;//倒数计时
    private static final int COUNT_DONW_OVER=150;//计时结束
    private static final int COUNT_DONW_TIME=5;//倒计时总时间
    private static final int COUNT_DONW_TIMEOUT=1;//超时

    private static boolean isRegSuccess=false;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_YZ_OK:
                    Toast.makeText(mBaseActivitySelf, "验证成功了！", Toast.LENGTH_SHORT).show();
//                    saveUser(mPhone,mPhone);
                    break;
                case CODE_GET_OK:
                    Toast.makeText(mBaseActivitySelf, "短信就来了,快去看！！", Toast.LENGTH_SHORT).show();
                    break;
                case CODE_ERROR:
                    Toast.makeText(mBaseActivitySelf, "验证失败！", Toast.LENGTH_SHORT).show();
                    break;
                case COUNT_DONW:
                    refreshCountDown();
                    break;
                case COUNT_DONW_OVER:
                    stopCountDown();
                    break;
                case COUNT_DONW_TIMEOUT:
                    Toast.makeText(mBaseActivitySelf, "验证码超时,请重新获取", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private RegEntity mEntity;

    @Override
    protected int addRootView() {
        return R.layout.activity_reg;


    }



    @Override
    protected void initNetData() {

    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected void success(RegEntity entity) {
        mEntity = entity;
        Toast.makeText(mBaseActivitySelf, "网络访问成功", Toast.LENGTH_SHORT).show();
        Log.d("RegActivity", "entity:" + entity);
        Intent intent=new Intent(mBaseActivitySelf,LogincAtivity.class);
        startActivity(intent);
    }

    @Override
    protected void failed(Throwable throwable) {
        Log.d("qq", "throwable:" + throwable);
    }

    @Override
    protected String gerUrl() {
        return BASE_URL+"registerUser";
    }
//
//    private void saveUser(String uid, String username) {
//
//        SPUtils spUtils=SPUtils.getInstance(Constant.SP_LOGIN_ACCOUNT);
//        spUtils.put("loginOK",true);
//        spUtils.put("uid",uid);
//        spUtils.put("username",username);
////        startActivity(new Intent(mBaseActivitySelf,UserCenterActivity.class));
//    }

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



        mImageView = (ImageView) findViewById(R.id.imageView);
        mEdtRegnameReg = (EditText) findViewById(R.id.edt_regname_reg);
        mEdtValidateReg = (EditText) findViewById(R.id.edt_validate_reg);
        mBtValidateReg = (Button) findViewById(R.id.bt_validate_reg);
        mEdtRegpwdReg = (EditText) findViewById(R.id.edt_regpwd_reg);
        mBtRegReg = (Button) findViewById(R.id.bt_reg_reg);
        mTvLoginReg = (TextView) findViewById(R.id.tv_login_reg);

        mBtValidateReg.setEnabled(false);

        mBtRegReg.setEnabled(false);


        if (mTimeLeft!=COUNT_DONW_TIME) {
            mBtValidateReg.setText(mTimeLeft+"s");
            startCountDown();
        }
    }
    boolean trueVal=false;
    boolean truePwd=false;
    @Override
    protected void initListener() {


        mSPUtils=SPUtils.getInstance(Constant.SP_LOGIN_COUNT_DOWN);
        //看看之前的倒计时完了没有
        int timeleft = mSPUtils.getInt("timeleft",0);
        //检查一下是不是还需要继续倒计时
        long timeend=mSPUtils.getLong("timeend",0);
        long timeNow=System.currentTimeMillis();
        long timePast=(timeNow-timeend)/1000;

        if (timePast>=timeleft) {
            //已经倒计时完毕
        }else {
            mTimeLeft= (int) (timeleft-timePast);

        }

        mBtValidateReg.setOnClickListener(this);
        mBtRegReg.setOnClickListener(this);
        mTvLoginReg.setOnClickListener(this);
        mEdtRegnameReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override//charSequence--文本    i--文本长度
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                boolean truePhone = CheckString.isTruePhone(charSequence.toString());
                if(truePhone){
                    //如果已经输入了正确的电话号码
                    mBtValidateReg.setEnabled(true);
                }else{
                    //如果电话号码不符合规范
                    mBtValidateReg.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEdtValidateReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                trueVal= CheckString.isTrueVal(charSequence.toString());
                if (trueVal&&truePwd) {
                    mBtRegReg.setEnabled(true);

                }else{
                    mBtRegReg.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEdtRegpwdReg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                truePwd = CheckString.isTruePwd(charSequence.toString());
                if (trueVal&&truePwd) {
                    mBtRegReg.setEnabled(true);

                }else{
                    mBtRegReg.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected boolean isNotUseTitle() {
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_validate_reg:
                startCountDown();
//                addParam();
                String usercodename=mEdtRegnameReg.getText().toString();
                new NetForCode().sendRegCode(usercodename);

                break;
            case R.id.bt_reg_reg:
                String username = mEdtRegnameReg.getText().toString();
                String userpwd=mEdtRegpwdReg.getText().toString();
                String code=mEdtValidateReg.getText().toString();


//                http://101.200.128.107:10028/repayment/user/registerUser?mobile=321412342314&password=412342134&code=412342314
                addParam("mobile",username);
                addParam("password",userpwd);
                addParam("code",code);
                execute();
                break;
            case R.id.tv_login_reg:
                Intent intent = new Intent(mBaseActivitySelf, LogincAtivity.class);
                startActivity(intent);
                break;

        }
    }

    //计时是否超时
    private void timeOut(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isRegSuccess) {
                    Message message=mHandler.obtainMessage();
                    message.what=COUNT_DONW_TIMEOUT;
                    mHandler.sendMessage(message);
                }
            }
        },1000*60*10);
    }


    //倒计时剩余时间
    private int mTimeLeft=COUNT_DONW_TIME;
    private Timer mTimer;
    private SPUtils mSPUtils;

    //开始倒计时
    private void startCountDown() {
        timeOut();
        mBtValidateReg.setEnabled(false);
        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message=mHandler.obtainMessage();

                mTimeLeft--;
                if (mTimeLeft<=0) {
                    message.what=COUNT_DONW_OVER;
                }else {

                    message.what=COUNT_DONW;
                    Log.e("qq", "正在倒计时"+mTimeLeft);
                }

                mHandler.sendMessage(message);
            }
        },0,1000);


    }


    //刷新到计时
    private void   refreshCountDown(){
        mBtValidateReg.setText(mTimeLeft+" s");

    }

    //停止倒计时
    private void   stopCountDown(){
        if (mSPUtils != null) {
            mSPUtils.put("timeleft",mTimeLeft);
            mSPUtils.put("timeend",System.currentTimeMillis());
            mTimeLeft=COUNT_DONW_TIME;
            mBtValidateReg.setEnabled(true);
            mBtValidateReg.setBackgroundResource(R.drawable.bt_code);
            mBtValidateReg.setText("获取验证码");
            if (mTimer != null) {
                mTimer.cancel();
                mTimer=null;
                System.gc();
            }
        }


    }


}
