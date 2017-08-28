package com.repayment.money.ui.adapter;


import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.repayment.money.R;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.IconUtil;
import com.repayment.money.entity.BankCardListItemEntity;
import com.repayment.money.ui.views.RepayPopupWindow;

import static com.yintong.secure.e.m.i.C;

public class PopupwindowSelectcardAdapter extends BaseAdapter {
    private List<BankCardListItemEntity.ResultObjBean> mEntities;
    private Context context;
    private LayoutInflater layoutInflater;

    private TextView mTvBankInfo;
    private CheckedTextView mImgChecked;
    private ImageView mImgBankIcon;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_select_cardl, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((BankCardListItemEntity.ResultObjBean) getItem(position), (ViewHolder) convertView.getTag());
        mTvBankInfo= (TextView) convertView.findViewById(R.id.tv_bankinfo_seletitem);
        mImgChecked= (CheckedTextView) convertView.findViewById(R.id.ctv_select_selectitem);
        mImgBankIcon= (ImageView) convertView.findViewById(R.id.img_bankicon_seletitem);

        mImgChecked .setBackground(null);
        if (position== RepayPopupWindow.mChageBankR) {
            mImgChecked.setBackground(convertView.getResources().getDrawable(R.drawable.checked));
        }

        String bankName = mEntities.get(position).getBankName();
        String bankCard = mEntities.get(position).getBankCard();
        mTvBankInfo.setText(BankNameUtil.bankNameFormat(bankName,bankCard));
        mImgBankIcon.setImageResource(IconUtil.getIcon(bankName));
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
