package com.repayment.money.ui.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.IconUtil;
import com.repayment.money.entity.BankCardListItemEntity;
import com.repayment.money.entity.BillDetailEntity;
import com.repayment.money.entity.BillListEntity;
import com.repayment.money.entity.RepayEntity;
import com.repayment.money.ui.dialog.HintDiglog;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by 11250 on 2017/8/22.
 */

public class RepayPopupWindow extends PopupWindow {
    private View conentView;
    private Activity context;
    private NetForJson mNetForHKJson;
    private NetForJson mNetForKKJson;
    private BankCardListItemEntity mBankCardListItemEntity;



    public static final int NET_HK = 1101;
    public static final int NET_KK = 120;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case NET_HK:
                    mNetForHKJson.execute();
                    break;
                case NET_KK:
                    mNetForKKJson.execute();
                    break;
            }
        }
    };


    private BillListEntity.ResultObjBean mRepayPopwindowEntity;

    public RepayPopupWindow(final Activity context, BillListEntity.ResultObjBean repayPopwindowEntity,BankCardListItemEntity bankCardListItemEntity) {
        super(context);
        this.context = context;
        mRepayPopwindowEntity = repayPopwindowEntity;
        mBankCardListItemEntity=bankCardListItemEntity;
        this.initPopupWindow();
    }

    private void initPopupWindow() {
        //使用View来引入布局
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_repay, null);
        //获取popupwindow的高度与宽度
        int height = context.getWindowManager().getDefaultDisplay().getHeight();
        int width = context.getWindowManager().getDefaultDisplay().getWidth();
        //设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //刷新状态
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable colorDrawable = new ColorDrawable(0000000000);
        //点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener,设置其他控件变化等操作
        this.setBackgroundDrawable(colorDrawable);
        this.setAnimationStyle(R.style.repaypopwindow_anim_style);
        Button bt_close = (Button) conentView.findViewById(R.id.bt_close_popw);
        Button bt_truepay = (Button) conentView.findViewById(R.id.bt_true_pay_popw);
        LinearLayout fkcard = (LinearLayout) conentView.findViewById(R.id.layout_fukuancard);
        daozhangBank = (TextView) conentView.findViewById(R.id.tv_daozhangBank_popw);
        huankuanmoney = (TextView) conentView.findViewById(R.id.tv_repaymoney_popw);
        daozhangImag = (ImageView) conentView.findViewById(R.id.img_daozheng_popw);
        mFukuanImg = (ImageView) conentView.findViewById(R.id.img_fukuan_popw);
        mTvFkcard = (TextView) conentView.findViewById(R.id.tv_fukuanbank_popw);
        daozhangImag.setImageResource(IconUtil.getIcon(mRepayPopwindowEntity.getBankName()));
        daozhangBank.setText(mRepayPopwindowEntity.getBankName());
        huankuanmoney.setText(mRepayPopwindowEntity.getMonthMoney() + "");

        fkbandcard = mBankCardListItemEntity.getResultObj().get(0).getBankCard();
        String bankName = mBankCardListItemEntity.getResultObj().get(0).getBankName();
        mTvFkcard.setText(BankNameUtil.bankNameFormat(bankName, fkbandcard));
        mFukuanImg.setImageResource(IconUtil.getIcon(bankName));

        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RepayPopupWindow.this.dismiss();
            }
        });
        bt_truepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doHK();
            }
        });
        fkcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChangeCardPopupWindow((Activity) context).showPopupWindow(context.findViewById(R.id.layout_billList));
            }
        });
    }

    TextView daozhangBank;
    TextView huankuanmoney;
    ImageView daozhangImag;
    ImageView mFukuanImg;
    TextView mTvFkcard;



    private void doKK(String hkNo, String bankCard) {
//        http://101.200.128.107:10028/repayment/huankuan/recoup?hkNo=2017082316215306610002&bankCard=6217855000001243443
        mNetForKKJson = new NetForJson("http://101.200.128.107:10028/repayment/huankuan/recoup", new NetForKK());
        mNetForKKJson.addParam("hkNo", hkNo);
        mNetForKKJson.addParam("bankCard", bankCard);
        mHandler.sendEmptyMessage(NET_KK);
    }

    public void doHK() {
        mNetForHKJson = new NetForJson("http://101.200.128.107:10028/repayment/order/findHuankuan", new NetForhk());
        mNetForHKJson.addParam("orderNo", mRepayPopwindowEntity.getOrderNo());
        mHandler.sendEmptyMessage(NET_HK);
    }

    public void showPopupWindow(View parent) {
        Log.d("qq", "--------------------------"+Constant.getTableuser().getUserNo());
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }


    private String fkbandcard;

    private class NetForhk extends NetOverListener<BillDetailEntity> {

        @Override
        public void success(BillDetailEntity billDetailEntity) {
            String hkNo = billDetailEntity.getResultObj().get(0).getHkNo();
            for (BillDetailEntity.ResultObjBean resultObjBean : billDetailEntity.getResultObj()) {
                if (resultObjBean.getHkStatus() == 0) {
                    hkNo = resultObjBean.getHkNo();
                    break;
                }
            }
            doKK(hkNo, fkbandcard);
        }

        @Override
        public void failed(Throwable throwable) {
            Toast.makeText(context, "HK网络访问失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }
    }

    private class NetForKK extends NetOverListener<RepayEntity> {

        @Override
        public void success(RepayEntity repayEntity) {
//            Toast.makeText(context, "支付请求已发送", Toast.LENGTH_SHORT).show();
            final HintDiglog hintDiglog = new HintDiglog(context);
            hintDiglog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hintDiglog.dismiss();
                    RepayPopupWindow.this.dismiss();
                }
            }, 3000);
        }

        @Override
        public void failed(Throwable throwable) {
            Toast.makeText(context, "KK网络访问失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }
    }
}
