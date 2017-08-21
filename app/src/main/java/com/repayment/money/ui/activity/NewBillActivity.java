package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.repayment.money.R;
import com.repayment.money.entity.NewBillEntity;

import java.util.ArrayList;
import java.util.List;

import static com.repayment.money.common.Constant.BASE_URL_NEWBILL;

public class NewBillActivity extends BaseActivityWithNet<NewBillEntity> implements View.OnClickListener {
    private Spinner mSpTypeNewbill;
    private Spinner mSpDateNewbill;
    private EditText mEdtRepayNumber;
    private EditText mEdtRepayNewbill;
    private EditText mEdtBankNewbill;
    private EditText mEdtCardNewbill;
    private ImageView mImgAddNewbill;
    private TextView mTvBankinfoNewbill;
    private ImageView mImgMoreNewbill;
    private Button mBtAddNewbill;
    private LinearLayout mXzNewbankBill;



    private ArrayAdapter mSpAdapter;
    private List<String> mRepayType=new ArrayList<>();
    private List<String> mRepayDate=new ArrayList<>();
    @Override
    protected int addRootView() {

        return R.layout.activity_new_bill;
    }

    @Override
    protected void initNetData() {

    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected void success(NewBillEntity entity) {
        Log.d("qq", "NewBill成功了");
        Log.d("qq", "entity:" + entity);
        if (entity.getCode()==1) {
            Toast.makeText(mBaseActivitySelf, "添加成功", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(mBaseActivitySelf, "添加失败,请检查输入信息", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void failed(Throwable throwable) {
        Log.d("qq", "NewBill失败了");
    }

    @Override
    protected String gerUrl() {
        return BASE_URL_NEWBILL;
    }

    @Override
    protected void initView() {
        setTitleCenter("新增账单");
        setTitleLeft("", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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


        mSpTypeNewbill = (Spinner) findViewById(R.id.sp_type_newbill);
        mSpDateNewbill = (Spinner) findViewById(R.id.sp_date_newbill);
        mEdtRepayNumber = (EditText) findViewById(R.id.edt_repay_number);
        mEdtRepayNewbill = (EditText) findViewById(R.id.edt_repay_newbill);
        mEdtBankNewbill = (EditText) findViewById(R.id.edt_bank_newbill);
        mEdtCardNewbill = (EditText) findViewById(R.id.edt_card_newbill);
        mImgAddNewbill = (ImageView) findViewById(R.id.img_add_newbill);
        mTvBankinfoNewbill = (TextView) findViewById(R.id.tv_bankinfo_newbill);
        mImgMoreNewbill = (ImageView) findViewById(R.id.img_more_newbill);
        mBtAddNewbill = (Button) findViewById(R.id.bt_add_newbill);
        mXzNewbankBill = (LinearLayout) findViewById(R.id.xz_newbank_bill);


        mRepayType.add("房贷");
        mRepayType.add("车贷");
        mRepayType.add("现金贷");
        mRepayType.add("花呗");
        mRepayType.add("京东白条");
        mSpAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mRepayType);
        mSpAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        mSpTypeNewbill.setAdapter((SpinnerAdapter) mSpAdapter);
        for (int i = 0; i < 31; i++) {
            mRepayDate.add(i+1+"");
        }
        mSpAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mRepayDate);
        mSpAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        mSpDateNewbill.setAdapter((SpinnerAdapter) mSpAdapter);



    }

    @Override
    protected void initListener() {
        mBtAddNewbill.setOnClickListener(this);
        mXzNewbankBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mBaseActivitySelf,BoundActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onClick(View view) {
        String userNo= "2017081913083103610004";
        String orderType= "0"  ;//mSpTypeNewbill.getSelectedItem().toString();
        String periodesType="M";
        String periodes = mEdtRepayNumber.getText().toString();
        String monthMoney=mEdtRepayNewbill.getText().toString();
        String hkDay=mSpDateNewbill.getSelectedItem().toString();
        String bankCard=mEdtBankNewbill.getText().toString();

        if (!(orderType.isEmpty()||periodesType.isEmpty()||periodes.isEmpty()||monthMoney.isEmpty()||hkDay.isEmpty()||bankCard.isEmpty())) {
            addParam("userNo",userNo);
            addParam("orderType",orderType);
            addParam("periodsType",periodesType);
            addParam("periods",periodes);
            addParam("monthMoney",monthMoney);
            addParam("hkDay",hkDay);
            addParam("bankCard",bankCard);
            execute();
        }else{
            //如果有一个没有填写
            Toast.makeText(mBaseActivitySelf, "内容不可为空", Toast.LENGTH_SHORT).show();
        }


//  /addOrder?userNo=2017081911412784240032&orderType=0&periodsType=M&periods=12&monthMoney=200&hkDay=12&bankCard=621785500000124
    }
}
