package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mylibrary.base.BaseActivityWithNet;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.entity.BankCardListItemEntity;
import com.repayment.money.ui.adapter.ItemBindCardMsgAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static org.greenrobot.eventbus.ThreadMode.MAIN;


public class CardControlActivity extends BaseActivityWithNet<BankCardListItemEntity> implements View.OnClickListener {
    private Button mBtAddCardActivity;
    private ListView mLvCardMsg;
    private LinearLayout mLayoutHideCardActivity;
    private LinearLayout mLayoutAddCardActivity;
    private ItemBindCardMsgAdapter mItemBindCardMsgAdapter;
    private List<BankCardListItemEntity.ResultObjBean> mResultCardList;


    @Override
    protected int addRootView() {
        return R.layout.activity_card_control;
    }


    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusBarColor));
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        mBtAddCardActivity = (Button) findViewById(R.id.bt_add_card_activity);
        mLvCardMsg = (ListView) findViewById(R.id.lv_card_msg);
        mLayoutHideCardActivity = (LinearLayout) findViewById(R.id.layout_hide_card_activity);
        mLayoutAddCardActivity = (LinearLayout) findViewById(R.id.layout_add_card_activity);

//        mLayoutHideCardActivity.setVisibility(View.GONE);
//        mLvCardMsg.setVisibility(View.VISIBLE);
        //先拉取数据   ?userNo=2017081913230826210005

        addParam("userNo", LogincAtivity.sTableUserNow.getUserNo());
        execute();

    }

    @Override
    protected void initListener() {
        mBtAddCardActivity.setOnClickListener(this);
        mLayoutAddCardActivity.setOnClickListener(this);

    }

    @Override
    protected boolean isNotUseTitle() {
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(mBaseActivitySelf,BoundActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initNetData() {
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected void success(BankCardListItemEntity entity) {
        mResultCardList = entity.getResultObj();
        if (entity.getResultObj().size()>=1){
            mLayoutHideCardActivity.setVisibility(View.GONE);
            mLvCardMsg.setVisibility(View.VISIBLE);
        }
        Constant.MRFKBankCard=mResultCardList.get(0).getBankCard();
        mItemBindCardMsgAdapter = new ItemBindCardMsgAdapter(mBaseActivitySelf,entity.getResultObj());
        mLvCardMsg.setAdapter(mItemBindCardMsgAdapter);
    }

    @Subscribe(threadMode=MAIN)
      public void onEvent(Boolean isOK){
          if (isOK){
              this.execute();
          }
      }


    @Override
    protected void failed(Throwable throwable) {
        Log.d("qq", "throwable:" + throwable);
    }

    @Override
    protected String gerUrl() {
        return Constant.CARD_BIND_LIST;
    }
}
