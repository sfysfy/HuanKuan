package com.repayment.money.ui.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.repayment.money.R;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.IconUtil;
import com.repayment.money.entity.BankCardListItemEntity;

public class PopupwindowSelectcardAdapter extends BaseAdapter {
    private List<BankCardListItemEntity.ResultObjBean> mEntities;
    private Context context;
    private LayoutInflater layoutInflater;

    public PopupwindowSelectcardAdapter(Context context, List<BankCardListItemEntity.ResultObjBean> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
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
            convertView = layoutInflater.inflate(R.layout.item_select_cardl, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((BankCardListItemEntity.ResultObjBean) getItem(position), (ViewHolder) convertView.getTag());



        return convertView;
    }

    private void initializeViews(BankCardListItemEntity.ResultObjBean entities, ViewHolder holder) {
        //TODO implement

    }

    protected class ViewHolder {
        private Button btBackPopw;
        private ListView lvBanklistItem;
        private LinearLayout layoutAddcardSelectcard;

        public ViewHolder(View view) {
            btBackPopw = (Button) view.findViewById(R.id.bt_back_popw);
            lvBanklistItem = (ListView) view.findViewById(R.id.lv_banklist_selectcard);
            layoutAddcardSelectcard = (LinearLayout) view.findViewById(R.id.layout_addcard_selectcard);
        }
    }
}
