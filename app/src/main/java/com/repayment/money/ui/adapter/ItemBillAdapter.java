package com.repayment.money.ui.adapter;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.UtilForItemBill;
import com.repayment.money.entity.BankCardListItemEntity;
import com.repayment.money.entity.BillListEntity;
import com.repayment.money.ui.activity.DetailsActivity;
import com.repayment.money.ui.views.RepayPopupWindow;

public class ItemBillAdapter extends BaseAdapter {

    private List<BillListEntity.ResultObjBean> mEntities;
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemBillAdapter(Context context, List<BillListEntity.ResultObjBean> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
    }


    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public BillListEntity.ResultObjBean getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_bill, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((BillListEntity.ResultObjBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private NetForJson mNetForCardListJson;
    ;
    private ViewHolder mHolder;

    private void initializeViews(final BillListEntity.ResultObjBean entities, final ViewHolder holder) {
        mHolder=holder;
        //TODO implement
        holder.imgBankiconItem.setImageResource(R.drawable.zhonghang);
        holder.tvBankinfoItem.setText(BankNameUtil.bankNameFormat(entities.getBankName(), entities.getBankCard()));
        holder.tvBilltypeItem.setText(UtilForItemBill.getBillType(entities.getOrderType()));
        holder.tvMoneyzItem.setText(UtilForItemBill.moneyZSFormat(entities.getMonthMoney()));
        holder.tvMoneyxItem.setText(UtilForItemBill.moneyXSFormat(entities.getMonthMoney()));
        holder.tvDayItem.setText(entities.getLatelyDay() + "");
        holder.tvBilldayItem.setText(entities.getLatelyDate());
        holder.btRepayItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里判断是否有银行卡,如果没有,先添加银行卡
                mNetForCardListJson = new NetForJson("http://101.200.128.107:10028/repayment/bank/findBankList", new NetForCardList(entities));
                mNetForCardListJson.addParam("userNo", Constant.getTableuser().getUserNo());
                mNetForCardListJson.execute();
                //------------------------------------------------

            }
        });
        holder.mLayoutBillList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderNo = entities.getOrderNo();
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("entity", entities);
                context.startActivity(intent);
            }
        });

    }

    protected class ViewHolder {
        private LinearLayout mLayoutBillList;
        private ImageView imgBankiconItem;
        private TextView tvBankinfoItem;
        private TextView tvBilltypeItem;
        private TextView tvMoneyzItem;
        private TextView tvMoneyxItem;
        private TextView tvDayItem;
        private TextView tvBilldayItem;
        private Button btRepayItem;

        public ViewHolder(View view) {
            mLayoutBillList = (LinearLayout) view.findViewById(R.id.layout_billList);
            imgBankiconItem = (ImageView) view.findViewById(R.id.img_bankicon_item);
            tvBankinfoItem = (TextView) view.findViewById(R.id.tv_bankinfo_item);
            tvBilltypeItem = (TextView) view.findViewById(R.id.tv_billtype_item);
            tvMoneyzItem = (TextView) view.findViewById(R.id.tv_moneyz_item);
            tvMoneyxItem = (TextView) view.findViewById(R.id.tv_moneyx_item);
            tvDayItem = (TextView) view.findViewById(R.id.tv_day_item);
            tvBilldayItem = (TextView) view.findViewById(R.id.tv_billday_item);
            btRepayItem = (Button) view.findViewById(R.id.bt_repay_item);
        }
    }

    private class NetForCardList extends NetOverListener<BankCardListItemEntity> {
       BillListEntity.ResultObjBean mEntity;
        public NetForCardList( BillListEntity.ResultObjBean entity){
            mEntity=entity;
        }
        @Override
        public void success(BankCardListItemEntity bankCardListItemEntity) {
            if (bankCardListItemEntity.getResultObj() != null) {
                RepayPopupWindow repayPopupWindow = new RepayPopupWindow((Activity) context, mEntity,bankCardListItemEntity);
                repayPopupWindow.showPopupWindow(mHolder.mLayoutBillList);
            } else {
                Toast.makeText(context, "请先添加银行卡", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void failed(Throwable throwable) {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }
    }
}
