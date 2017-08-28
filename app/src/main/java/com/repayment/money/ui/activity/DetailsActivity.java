package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.IconUtil;
import com.repayment.money.common.utils.UtilForItemBill;
import com.repayment.money.common.utils.pay.FormatDateUtils;
import com.repayment.money.entity.BillDetailEntity;
import com.repayment.money.entity.BillListEntity;

public class DetailsActivity extends BaseActivityWithNet<BillDetailEntity> {
    private TextView mTvMoneyActivityDetail;
    private TextView mTvMoney2ActivityDetail;
    private TextView mTvRepayDateActivityDetail;
    private ImageView mImgIconActivityDetail;
    private TextView mTvBankNameBankActivityDetail;
    private TextView mTvMoneyDetailActivity;
    private TextView mTvStateDetailActivity;
    private TextView mTvTimeActivityDetail;
    private Intent mIntent;
    private BillListEntity.ResultObjBean mEntitie;
    private long mRepayMentTime;
    private int mHkStatus;
    private float mMonthMoney;


    @Override
    protected int addRootView() {
        return R.layout.activity_details;
    }

    @Override
    protected void initNetData() {

    }

    @Override
    protected void initLocalData() {
        mIntent = this.getIntent();
        mEntitie = (BillListEntity.ResultObjBean) mIntent.getSerializableExtra("entity");

    }

    @Override
    protected void success(BillDetailEntity entity) {
        mMonthMoney = entity.getResultObj().get(0).getMonthMoney();

        mHkStatus = entity.getResultObj().get(0).getHkStatus();
        if (mHkStatus==0) {
            mRepayMentTime = entity.getResultObj().get(0).getRepayMentTime();
        }else{
            mRepayMentTime = entity.getResultObj().get(0).getRealRepayMentTime();
        }
        doShowMsg();
    }

    private void doShowMsg() {
        String moneyZSFormat = UtilForItemBill.moneyZSFormat(mEntitie.getMonthMoney());
        String moneyXSFormat = UtilForItemBill.moneyXSFormat(mEntitie.getMonthMoney());
        mTvMoneyActivityDetail.setText(moneyZSFormat);
        mTvMoney2ActivityDetail.setText(moneyXSFormat);
        mTvRepayDateActivityDetail.setText(mEntitie.getLatelyDate());
        int icon = IconUtil.getIcon(mEntitie.getBankName());
        mImgIconActivityDetail.setImageResource(icon);
        String nameFormat = BankNameUtil.bankNameFormat(mEntitie.getBankName(), mEntitie.getBankCard());
        mTvBankNameBankActivityDetail.setText(nameFormat);

        mTvMoneyDetailActivity.setText(mMonthMoney + "");
        String formatDate = FormatDateUtils.formatDate(mRepayMentTime);
        mTvTimeActivityDetail.setText(formatDate);

        String getStatue = doGetStatue(mHkStatus);
        mTvStateDetailActivity.setText(getStatue);

    }

    private String doGetStatue(int hkStatus) {
        if (hkStatus == 0) {
            return "未还款";
        }else if (hkStatus==1){
            return "已还款";
        }else if (hkStatus==3){
            return "还款处理中";
        }else if (hkStatus==4){
            return "还款失败";
        }
        return "";
    }

    @Override
    protected void failed(Throwable throwable) {
        Toast.makeText(mBaseActivitySelf, "网络或者网络异常,拉取信息失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String gerUrl() {
        return Constant.BILL_DETAIL_URL;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusBarColor));
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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
                finish();
            }
        });
        setTitleCenter("账单明细");
        if (mEntitie != null) {
            doGetDetial();
        }


    }

    private void doGetDetial() {
        addParam("orderNo", mEntitie.getOrderNo());
        execute();
    }

    @Override
    protected void initListener() {

    }


}
