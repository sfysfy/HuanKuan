package com.repayment.money.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.repayment.money.R;
import com.repayment.money.common.utils.IconUtil;
import com.repayment.money.entity.BankCardListItemEntity;

import java.util.List;

public class ItemBindCardMsgAdapter extends BaseAdapter {

    private  List<BankCardListItemEntity.ResultObjBean> mEntities;

    private Context mContext;
    private LayoutInflater layoutInflater;

    public ItemBindCardMsgAdapter(Context context, List<BankCardListItemEntity.ResultObjBean> entities) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public BankCardListItemEntity.ResultObjBean getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_bind_card_msg, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((BankCardListItemEntity.ResultObjBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(BankCardListItemEntity.ResultObjBean entity, ViewHolder holder,int position) {
        //TODO implement
        holder.imgIconBankBind.setImageResource(IconUtil.getIcon(entity.getBankName()));
        holder.tvBankNameBing.setText(entity.getBankName());
        String cardType=null;
        if (entity.getCardType()==2){
            cardType="储蓄卡";
        } else if (entity.getCardType()==3){
            cardType="信用卡";
        }
        holder.tvBankTypeBing.setText(cardType);
        holder.mTvCardNumItem.setText(entity.getBankCard());
    }

    protected class ViewHolder {
        private RelativeLayout reaLayoutItemCard;
        private ImageView imgIconBankBind;
        private TextView tvBankNameBing;
        private TextView tvBankTypeBing;
        private TextView mTvCardNumItem;


        public ViewHolder(View view) {
            reaLayoutItemCard = (RelativeLayout) view.findViewById(R.id.rea_layout_item_card);
            imgIconBankBind = (ImageView) view.findViewById(R.id.img_icon_bank_bind);
            tvBankNameBing = (TextView) view.findViewById(R.id.tv_bank_name_bing);
            tvBankTypeBing = (TextView) view.findViewById(R.id.tv_bank_type_bing);
            mTvCardNumItem = (TextView) view.findViewById(R.id.tv_card_num_item);

        }
    }
}
