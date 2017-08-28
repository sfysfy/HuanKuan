package com.repayment.money.ui.views;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.base.BaseFragment;
import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.IconUtil;
import com.repayment.money.entity.BankCardListItemEntity;
import com.repayment.money.ui.activity.CardControlActivity;
import com.repayment.money.ui.adapter.ItemBillAdapter;
import com.repayment.money.ui.adapter.PopupwindowSelectcardAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.example.mylibrary.util.FragmentUtils.hideFragment;
import static com.example.mylibrary.util.FragmentUtils.showFragment;
import static com.yintong.secure.e.m.j.T;

/**
 * Created by 11250 on 2017/8/22.
 */

public class ChangeCardPopupWindow extends PopupWindow {
    private View conentView;
    private Activity context;
    private BankCardListItemEntity mBankCardListItemEntity ;
    private PopupwindowSelectcardAdapter mItemCardAdapter;
    private Button mBtBackPopw;
    private LinearLayout mLayoutAddcardSelectcard;



   private TextView mFkCard;
    private ImageView mFkImg;
    public void setFkCard(TextView fkCard,ImageView fkImg) {
        mFkCard = fkCard;
        mFkImg=fkImg;
    }

    private List<BankCardListItemEntity.ResultObjBean> mCardList;

    public ChangeCardPopupWindow(final Activity context,BankCardListItemEntity bankCardListItemEntity){
        super(context);
        mBankCardListItemEntity=bankCardListItemEntity;
        mCardList=bankCardListItemEntity.getResultObj();
        this.context=context;
        this.initPopupWindow();
    }

    ListView bankList;
    private void initPopupWindow() {

        //使用View来引入布局
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_selectcard, null);

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
        ColorDrawable colorDrawable=new ColorDrawable(0000000000);
        //点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener,设置其他控件变化等操作
        this.setBackgroundDrawable(colorDrawable);
        this.setAnimationStyle(R.style.repaypopwindow_anim_style);
        bankList = (ListView) conentView.findViewById(R.id.lv_banklist_selectcard);
        mBtBackPopw = (Button) conentView.findViewById(R.id.bt_back_popw);
        mLayoutAddcardSelectcard = (LinearLayout)conentView. findViewById(R.id.layout_addcard_selectcard);

        List<BankCardListItemEntity.ResultObjBean> resultObj = mBankCardListItemEntity.getResultObj();
        mItemCardAdapter = new PopupwindowSelectcardAdapter(context,resultObj);
        bankList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RepayPopupWindow.mChageBankR=i;
                Log.e("qq", "onItemClick:ahfadfjlkadsjlfjdaslkjflajsdflkadsfasdf "+i );
                mFkCard.setText(BankNameUtil.bankNameFormat(mBankCardListItemEntity.getResultObj().get(i).getBankName(),mBankCardListItemEntity.getResultObj().get(i).getBankCard()));
                mFkImg.setImageResource(IconUtil.getIcon(mBankCardListItemEntity.getResultObj().get(i).getBankName()));
                bankList.setAdapter(mItemCardAdapter);
            }
        });
        bankList.setAdapter(mItemCardAdapter);

        mBtBackPopw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeCardPopupWindow.this.dismiss();
            }
        });
        mLayoutAddcardSelectcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CardControlActivity.class);
                context.startActivity(intent);
            }
        });
    }


    public void showPopupWindow(View parent){
        if (parent.getVisibility()== View.GONE) {
            this.showAtLocation(parent,0,0,0);
        }else{
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent,Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        }
    }
}
