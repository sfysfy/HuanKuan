package com.repayment.money.ui.adapter;


import java.util.ArrayList;
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

import com.repayment.money.R;
import com.repayment.money.common.utils.UtilForBankCardNum;
import com.repayment.money.entity.BillListEntity;
import com.repayment.money.ui.activity.DetailsActivity;
import com.repayment.money.ui.fragment.HomeFragment;
import com.repayment.money.ui.views.RepayPopupWindow;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

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

    private void initializeViews(final BillListEntity.ResultObjBean entities, final ViewHolder holder) {
        //TODO implement
        Log.d("qq", "entities:" + entities);
        holder.imgBankiconItem.setImageResource(R.drawable.zhonghang);
//        holder.tvBankinfoItem.setText("中国银行    3443");
        holder.tvBankinfoItem.setText(UtilForBankCardNum.fourCardNumkh(entities.getBankCard()));
        holder.tvBilltypeItem.setText("房贷");
        holder.tvMoneyzItem.setText(entities.getMonthMoney()+"");
        holder.tvMoneyxItem.setText("00");
        holder.tvDayItem.setText("7");
        holder.tvBilldayItem.setText("8-19");
        holder.btRepayItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RepayPopupWindow((Activity) context).showPopupWindow(holder.mLayoutBillList);
            }
        });
        holder.mLayoutBillList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderNo = entities.getOrderNo();
                Log.e("qq", "-我是订单号-----------------"+orderNo );
                Intent intent=new Intent(context,DetailsActivity.class);
                intent.putExtra("orderNo",orderNo);
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
}
