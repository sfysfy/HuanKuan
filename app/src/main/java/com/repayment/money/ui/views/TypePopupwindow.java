package com.repayment.money.ui.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.entity.BankCardListItemEntity;
import com.repayment.money.ui.adapter.PopupwindowSelectcardAdapter;

import java.util.List;

/**
 * Created by 11250 on 2017/8/25.
 */

public class TypePopupwindow extends PopupWindow {
    private View conentView;
    private Activity context;

    public TypePopupwindow(final Activity context){
        super(context);
        this.context=context;
        this.initPopupWindow();
    }

    private void initPopupWindow() {
        //使用View来引入布局
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.item_typelist, null);

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
    }


    public void showPopupWindow(View parent){
        if (parent.getVisibility()== View.GONE) {
            this.showAtLocation(parent,0,0,0);
        }else{
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        }
    }


}
