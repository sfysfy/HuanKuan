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

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.R;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.UtilForItemBill;
import com.repayment.money.entity.BankCardEntity;
import com.repayment.money.entity.BillListEntity;
import com.repayment.money.entity.RepayPopwindowEntity;
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

    private NetForJson mNetForJson;
    private ViewHolder mViewHolder;
    private BillListEntity.ResultObjBean mResultObjBean;
    private void initializeViews(final BillListEntity.ResultObjBean entities, final ViewHolder holder) {
        //TODO implement
        mViewHolder=holder;
        mResultObjBean=entities;
        mNetForJson=new NetForJson("http://101.200.128.107:10028/repayment/bank/bankCardBin",new NetForCardName());
        mNetForJson.addParam("bankCard",entities.getBankCard());
        mNetForJson.execute();


        Log.d("qq", "entities:" + entities);


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
            mLayoutBillList = (LinearLayout)view.findViewById(R.id.layout_billList);
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

    private class NetForCardName extends NetOverListener<BankCardEntity> {
        @Override
        public void success(final BankCardEntity bankCardEntity) {
            if (bankCardEntity.getResultObj()!=null){
                mViewHolder.imgBankiconItem.setImageResource(R.drawable.zhonghang);
//        holder.tvBankinfoItem.setText("中国银行    3443");
                mViewHolder.tvBankinfoItem.setText(BankNameUtil.bankNameFormat(bankCardEntity.getResultObj().getBank_name(),mResultObjBean.getBankCard()));
                mViewHolder.tvBilltypeItem.setText(UtilForItemBill.getBillType(mResultObjBean.getOrderType()));
                mViewHolder.tvMoneyzItem.setText(UtilForItemBill.moneyZSFormat(mResultObjBean.getMonthMoney()));
                mViewHolder.tvMoneyxItem.setText(UtilForItemBill.moneyXSFormat(mResultObjBean.getMonthMoney()));
                mViewHolder.tvDayItem.setText("7");
                mViewHolder.tvBilldayItem.setText("8-19");
                mViewHolder.btRepayItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RepayPopwindowEntity entity=new RepayPopwindowEntity();
                        entity.setBankName(bankCardEntity.getResultObj().getBank_name());
                        entity.setMoneyNum(mResultObjBean.getMonthMoney()+"");
                        RepayPopupWindow repayPopupWindow = new RepayPopupWindow((Activity) context,entity);
                        repayPopupWindow.showPopupWindow(mViewHolder.mLayoutBillList);
                    }
                });
                mViewHolder.mLayoutBillList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String orderNo = mResultObjBean.getOrderNo();
                        Log.e("qq", "-我是订单号-----------------"+orderNo );
                        Intent intent=new Intent(context,DetailsActivity.class);
                        intent.putExtra("orderNo",orderNo);
                        context.startActivity(intent);
                    }
                });
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
