package com.repayment.money.ui.adapter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.repayment.money.R;
import com.repayment.money.entity.BillListEntity;
import com.repayment.money.ui.fragment.HomeFragment;

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

    private void initializeViews(BillListEntity.ResultObjBean entities, ViewHolder holder) {
        //TODO implement
        holder.imgBankiconItem.setImageResource(R.drawable.zhonghang);
        holder.tvBankinfoItem.setText("中国银行    3443");
        holder.tvBilltypeItem.setText("房贷");
        holder.tvMoneyzItem.setText("2000");
        holder.tvMoneyxItem.setText("00");
        holder.tvDayItem.setText("7");
        holder.tvBilldayItem.setText("8-19");

    }

    protected class ViewHolder {
        private ImageView imgBankiconItem;
        private TextView tvBankinfoItem;
        private TextView tvBilltypeItem;
        private TextView tvMoneyzItem;
        private TextView tvMoneyxItem;
        private TextView tvDayItem;
        private TextView tvBilldayItem;
        private Button btRepayItem;

        public ViewHolder(View view) {
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
}
