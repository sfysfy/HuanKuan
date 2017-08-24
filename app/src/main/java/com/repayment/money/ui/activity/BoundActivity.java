package com.repayment.money.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.example.mylibrary.util.SPUtils;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.NetForBankCard;
import com.repayment.money.common.utils.pay.BaseHelper;
import com.repayment.money.common.utils.pay.Constants;
import com.repayment.money.common.utils.pay.MobileSecurePayer;
import com.repayment.money.db.TableUser;
import com.repayment.money.entity.BindBankCardEntity;
import com.repayment.money.entity.CheckBankCardEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

public class  BoundActivity extends BaseActivityWithNet<CheckBankCardEntity> {

    private EditText mEdtNameActivityBound;
    private EditText mEdtIdCardBoundActivity;
    private LinearLayout mLayoutIdCardActivityBound;
    private EditText mEdtNumCardBoundActivity;

    private List<String> mBankName=new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    private EditText mEdtNumPhoneBoundActivity;
    private Button mBtDoActivityBound;
    private Spinner mSpinnerNameBankBoundActivity;
    private MobileSecurePayer mPayer;
    private Handler mHandler = createHandler();
    private boolean isTestServer = false;

    private String mSelectBankName;
    private  String mNo_agree;

    private String mBankCardNum;
    private String mPhoneNum;
    private String mIdCard;
    private TableUser mTableUser;
    private String mName;
    private NetForJson mNetForJson1;
    private NetForJson mNetForJson2;
    private String mBankCardType; //2   储蓄卡    3,信用卡

    boolean isDoPostCardBind=false;




    public ArrayAdapter<String> getAdapter() {
        return mAdapter;
    }



    private Handler createHandler() {
        return new Handler() {
            public void handleMessage(Message msg) {
                String strRet = (String) msg.obj;
                switch (msg.what) {
                    case Constants.RQF_PAY:
                    case Constants.RQF_SIGN:
                    {
                        JSONObject objContent = BaseHelper.string2JSON(strRet);
                        System.out.println("strRet = " + strRet);
                        String retCode = objContent.optString("ret_code");
                        String retMsg = objContent.optString("ret_msg");

                        // 成功
                        if (Constants.RET_CODE_SUCCESS.equals(retCode)) {

                            BaseHelper.showDialog(BoundActivity.this, "提示",
                                    "支付成功，交易状态码：" + retCode+" 返回报文:"+strRet,
                                    android.R.drawable.ic_dialog_alert);
                            String[] split = strRet.split(",");
                            String[] split1 = split[split.length - 1].split(":");
                             mNo_agree = split1[1].substring(1, split1[1].length() - 2);
                            System.out.println("no_agree = " + mNo_agree);
                            //http://101.200.128.107:10028/repayment/bank/bindBankCard?
                            // mobile=15731660437&
                            // bankCard=6217855000001243443&
                            // bankName=%E4%B8%AD%E5%9B%BD%E9%93%B6%E8%A1%8C&
                            // idCard=152105199508192115&
                            // userNo=2017081913230826210005&
                            // channel=0&
                            // noAgree=2017082086681269&
                            // name=%E9%9A%8B%E5%B2%A9
                            //cardType=2
                               mNetForJson1.addParam("mobile",mPhoneNum);
                               mNetForJson1.addParam("bankCard",mBankCardNum);
                               mNetForJson1.addParam("bankName",mSelectBankName);
                               mNetForJson1.addParam("idCard",mIdCard);
                               mNetForJson1.addParam("userNo",mTableUser.getUserNo());
                               mNetForJson1.addParam("channel",mTableUser.getChannel());
                               mNetForJson1.addParam("noAgree",mNo_agree);
                               mNetForJson1.addParam("name",mName);
                               mNetForJson1.addParam("cardType",2);
                               mNetForJson1.execute();
                               Toast.makeText(mBaseActivitySelf, "值执行完了啊啊啊", Toast.LENGTH_SHORT).show();
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
        mEdtNumPhoneBoundActivity = (EditText) findViewById(R.id.edt_num_phone_bound_activity);
        mSpinnerNameBankBoundActivity = (Spinner) findViewById(R.id.spinner_name_bank_bound_activity);
        addSpinner();
        mAdapter=new ArrayAdapter<String>(mBaseActivitySelf,android.R.layout.simple_list_item_1,mBankName);
        mSpinnerNameBankBoundActivity.setAdapter(mAdapter);


        //mLayoutIdCardActivityBound.setVisibility(View.GONE);

    }

    @Subscribe()
      public void onEvent(String bankCardType){
        System.out.println("bankCardType ===================== " + bankCardType);
        mBankCardType=bankCardType;
        if (bankCardType != null) {
            System.out.println(" =---------------------------------====== " );
           doCheckCard();
        }

      }

    private void doCheckCard() {
            if (mTableUser!=null){
                addParam("mobile",mPhoneNum);
                addParam("bankCard",mBankCardNum);
                addParam("bankName",mSelectBankName);
                addParam("idCard",mIdCard);
                addParam("userNo",mTableUser.getUserNo());
                addParam("channel",mTableUser.getChannel());
                addParam("name",mName);
                execute();
            }
    }

    private void addSpinner() {
        mBankName.add("中国农业银行");
        mBankName.add("交通银行");
        mBankName.add("中国工商银行");
        mBankName.add("中国邮政储蓄银行");
        mBankName.add("上海浦发银行");
        mBankName.add("平安银行");

        mBankName.add("广东发展银行");
        mBankName.add("招商银行");
        mBankName.add("中国银行");
        mBankName.add("光大银行");
        mBankName.add("兴业银行");
        mBankName.add("中信银行");

        mBankName.add("华夏银行");
        mBankName.add("杭州银行");
        mBankName.add("北京银行");
        mBankName.add("浙商银行");
        mBankName.add("上海银行");
        mBankName.add("宁波银行");

//        mBankName.add("中国农业银行");
//        mBankName.add("中国农业银行");


    }

    @Override
    protected void initListener() {
        mBtDoActivityBound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBankCardNum = mEdtNumCardBoundActivity.getText().toString().trim();
                mPhoneNum = mEdtNumPhoneBoundActivity.getText().toString().trim();
                mIdCard=mEdtIdCardBoundActivity.getText().toString().trim();
                mName=mEdtNameActivityBound.getText().toString().trim();
                try {
                     mTableUser = LogincAtivity.mDbManager.selector(TableUser.class).where("phone", "=", mPhoneNum).findFirst();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                NetForBankCard n=new NetForBankCard();
                n.yzBankCard(mBankCardNum);
                System.out.println("mBankCardNum = " + mBankCardNum);
            }
        });

        mSpinnerNameBankBoundActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectBankName = mBankName.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void initNetData() {
         mPayer= new MobileSecurePayer();
        //绑卡的回调
        mNetForJson1=new NetForJson("http://101.200.128.107:10028/repayment/bank/bindBankCard", new NetOverListener<BindBankCardEntity>() {
            @Override
            public void success(BindBankCardEntity bindBankCardEntity) {
                System.out.println("bindBankCardEntity ===========++++= " + bindBankCardEntity);
                if (bindBankCardEntity.getCode()==1){
                    Toast.makeText(mBaseActivitySelf, "绑定成功", Toast.LENGTH_SHORT).show();
                    //将当前账户的   用户姓名   和  身份证号码  存储
                    SPUtils.getInstance(Constant.SP_USER_MSG).put("name",mName);
                    SPUtils.getInstance(Constant.SP_USER_MSG).put("idCard",mIdCard);
                    finish();
                }else{

                }

            }

            @Override
            public void failed(Throwable throwable) {
                Toast.makeText(mBaseActivitySelf, "服务器异常", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }
        });
        mNetForJson1.setClassEntity(BindBankCardEntity.class);

    }

    @Override
    protected void initLocalData() {
        EventBus.getDefault().register(mBaseActivitySelf);

    }

    @Override
    protected void success(CheckBankCardEntity entity) {
        //检卡成功
        System.out.println("entity = " + entity);
        Toast.makeText(mBaseActivitySelf, "提交信息成功", Toast.LENGTH_SHORT).show();
        if (entity.getCode()==1){
            String resultObj = entity.getResultObj();
            try {
                JSONObject jsonObject=new JSONObject(resultObj);
//            System.out.println("jsonObject = " + jsonObject);
                mPayer.setCallbackHandler(mHandler, Constants.RQF_PAY);
                mPayer.setTestMode(isTestServer);
                mPayer.doTokenSign(jsonObject,mBaseActivitySelf);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(mBaseActivitySelf, "该银行卡已经绑定了", Toast.LENGTH_SHORT).show();
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
