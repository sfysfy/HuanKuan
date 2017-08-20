package com.repayment.money.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mylibrary.base.BaseActivity;
import com.repayment.money.R;

public class NewBillActivity extends BaseActivity {
    private Spinner mSpTypeNewbill;
    private Spinner mSpDateNewbill;
    private EditText mEdtRepayNewbill;
    private EditText mEdtBankNewbill;
    private EditText mEdtCardNewbill;
    private ImageView mImgAddNewbill;
    private TextView mTvBankinfoNewbill;
    private ImageView mImgMoreNewbill;
    private Button mBtAddNewbill;


    private String[] mTypes={"房贷","车贷","现金贷","花呗","京东白条"};
    private Adapter mSpTypeAdapter;
    @Override
    protected int addRootView() {

        return R.layout.activity_new_bill;
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
        setTitleCenter("新增账单");
        setTitleLeft("", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mSpTypeNewbill = (Spinner) findViewById(R.id.sp_type_newbill);
        mSpDateNewbill = (Spinner) findViewById(R.id.sp_date_newbill);
        mEdtRepayNewbill = (EditText) findViewById(R.id.edt_repay_newbill);
        mEdtBankNewbill = (EditText) findViewById(R.id.edt_bank_newbill);
        mEdtCardNewbill = (EditText) findViewById(R.id.edt_card_newbill);
        mImgAddNewbill = (ImageView) findViewById(R.id.img_add_newbill);
        mTvBankinfoNewbill = (TextView) findViewById(R.id.tv_bankinfo_newbill);
        mImgMoreNewbill = (ImageView) findViewById(R.id.img_more_newbill);
        mBtAddNewbill = (Button) findViewById(R.id.bt_add_newbill);


    }

    @Override
    protected void initListener() {

    }


}
