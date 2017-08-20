package com.repayment.money.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.pay.BaseHelper;
import com.repayment.money.common.utils.pay.Constants;
import com.repayment.money.common.utils.pay.MobileSecurePayer;
import com.repayment.money.db.TableUser;
import com.repayment.money.entity.CheckBankCardEntity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.ex.DbException;

public class BoundActivity extends BaseActivityWithNet<CheckBankCardEntity> {

    private EditText mEdtNameActivityBound;
    private EditText mEdtIdCardBoundActivity;
    private LinearLayout mLayoutIdCardActivityBound;
    private EditText mEdtNumCardBoundActivity;
    private EditText mEdtNameBankBoundActivity;
    private EditText mEdtNumPhoneBoundActivity;
    private Button mBtDoActivityBound;

    private MobileSecurePayer mPayer;

    private Handler mHandler = createHandler();
    private boolean isTestServer = false;
    private Handler createHandler() {
        return new Handler() {
            public void handleMessage(Message msg) {
                String strRet = (String) msg.obj;
                switch (msg.what) {
                    case Constants.RQF_PAY:
                    case Constants.RQF_SIGN:
                    {
                        JSONObject objContent = BaseHelper.string2JSON(strRet);
                        String retCode = objContent.optString("ret_code");
                        String retMsg = objContent.optString("ret_msg");

                        // 成功
                        if (Constants.RET_CODE_SUCCESS.equals(retCode)) {

                            BaseHelper.showDialog(BoundActivity.this, "提示",
                                    "支付成功，交易状态码：" + retCode+" 返回报文:"+strRet,
                                    android.R.drawable.ic_dialog_alert);
                        }  else {
                            // TODO 失败
                            BaseHelper.showDialog(BoundActivity.this, "错误提示", retMsg
                                            + "，交易状态码:" + retCode+" 返回报文:"+strRet,
                                    android.R.drawable.ic_dialog_alert);
                        }
                    }
                    break;
                }
                super.handleMessage(msg);
            }
        };
    }


    @Override
    protected int addRootView() {
        return R.layout.activity_bound;
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
//          http://101.200.128.107:10028/repayment/bank/checkBankCard
// mobile=17733478684&
// bankCard=6228481006488020062&
// bankName=%E4%B8%AD%E5%9B%BD%E5%86%9C%E4%B8%9A%E9%93%B6%E8%A1%8C&
// idCard=130529199302101318
// &userNo=2017081911412784810001&
// channel=0&
// name=%E9%A9%AC%E5%BD%A6%E8%99%8E
                String bankCard = mEdtNumCardBoundActivity.getText().toString().trim();
                String mobile = mEdtNumPhoneBoundActivity.getText().toString().trim();
                String bankName = mEdtNameBankBoundActivity.getText().toString().trim();
                String idCard=mEdtIdCardBoundActivity.getText().toString().trim();
                TableUser user=null;
                String name=mEdtNameActivityBound.getText().toString().trim();

                try {
                     user = LogincAtivity.mDbManager.selector(TableUser.class).where("phone", "=", mobile).findFirst();
                    System.out.println("user ======== " + user);
                } catch (DbException e) {
                    e.printStackTrace();
                }
//
                if (user!=null){
                    addParam("mobile",mobile);
                    addParam("bankCard",bankCard);
                    addParam("bankName",bankName);
                    addParam("idCard",idCard);
                    addParam("userNo",user.getUserNo());
                    addParam("channel",user.getChannel());
                    addParam("name",name);
                    execute();
                }

            }
        });
    }

    @Override
    protected void initNetData() {
         mPayer= new MobileSecurePayer();

    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected void success(CheckBankCardEntity entity) {
        System.out.println("entity = " + entity);
        Toast.makeText(mBaseActivitySelf, "提交信息成功", Toast.LENGTH_SHORT).show();
        String resultObj = entity.getResultObj();
        try {
            JSONObject jsonObject=new JSONObject(resultObj);
            mPayer.setCallbackHandler(mHandler, Constants.RQF_PAY);
            mPayer.setTestMode(isTestServer);
            mPayer.doTokenSign(jsonObject,mBaseActivitySelf);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void failed(Throwable throwable) {
        Toast.makeText(mBaseActivitySelf, "服务器异常", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected String gerUrl() {
        return Constant.BASE_URL_CHECKBANKCARD;
    }
}
