package com.repayment.money.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.entity.BillDetailEntity;

public class DetailsActivity extends BaseActivityWithNet<BillDetailEntity> {
    private TextView mTvMoneyActivityDetail;
    private TextView mTvMoney2ActivityDetail;
    private TextView mTvRepayDateActivityDetail;
    private ImageView mImgIconActivityDetail;
    private TextView mTvBankNameBankActivityDetail;
    private TextView mTvMoneyDetailActivity;
    private TextView mTvStateDetailActivity;
    private TextView mTvTimeActivityDetail;




    @Override
    protected int addRootView() {
        return R.layout.activity_details;
    }

    @Override
    protected void initNetData() {

    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected void success(BillDetailEntity entity) {

    }

    @Override
    protected void failed(Throwable throwable) {

    }

    @Override
    protected String gerUrl() {
        return Constant.BILL_DETAIL_URL;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
////                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusBarColor));
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        mTvMoneyActivityDetail = (TextView) findViewById(R.id.tv_money_activity_detail);
        mTvMoney2ActivityDetail = (TextView) findViewById(R.id.tv_money2_activity_detail);
        mTvRepayDateActivityDetail = (TextView) findViewById(R.id.tv_repay_date_activity_detail);
        mImgIconActivityDetail = (ImageView) findViewById(R.id.img_icon_activity_detail);
        mTvBankNameBankActivityDetail = (TextView) findViewById(R.id.tv_bank_name_bank_activity_detail);
        mTvMoneyDetailActivity = (TextView) findViewById(R.id.tv_money_detail_activity);
        mTvStateDetailActivity = (TextView) findViewById(R.id.tv_state_detail_activity);
        mTvTimeActivityDetail = (TextView) findViewById(R.id.tv_time_activity_detail);


        setTitleLeft("", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setTitleCenter("账单明细");
        doGetDetial();

    }

    private void doGetDetial() {
//        addParam("orderNo",);
        execute();
    }

    @Override
    protected void initListener() {

    }


}
