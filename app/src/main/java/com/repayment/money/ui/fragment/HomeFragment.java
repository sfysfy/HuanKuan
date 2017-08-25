package com.repayment.money.ui.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.base.BaseFragmentWithNet;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.db.TableUser;
import com.repayment.money.entity.BillListEntity;
import com.repayment.money.ui.activity.LogincAtivity;
import com.repayment.money.ui.activity.NewBillActivity;
import com.repayment.money.ui.adapter.ItemBillAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.ex.DbException;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.yintong.secure.e.m.i.G;

public class HomeFragment extends BaseFragmentWithNet<BillListEntity> {
    private List<BillListEntity.ResultObjBean> mProductlist;
    private ListView mLvMsgActivityHome;
    private ItemBillAdapter mItemBillAdapter;
    private LinearLayout mBtNewBillActivity;


    private ImageView mImgShoutu;
    private TextView mTvShowActivityHome1;
    private TextView mTvShowActivityHome2;

    @Subscribe
    public void onEvent(String sx){
        if ("刷新".equals(sx)) {
            this.execute();
        }
    }


    @Override
    protected int addRootView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initNetData() {
        EventBus.getDefault().register(this);
        TableUser user=Constant.getTableuser();

        if (user!=null) {
            String userNo=user.getUserNo();
            addParam("userNo",userNo);
            execute();
        }else{

        }
    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected void success(BillListEntity entity) {
        if (entity.getResultObj()!=null){
            mLvMsgActivityHome.setVisibility(View.VISIBLE);
            mImgShoutu.setVisibility(View.GONE);
            mTvShowActivityHome1.setVisibility(View.GONE);
            mTvShowActivityHome2.setVisibility(View.GONE);

            if (entity.getResultObj().size()>0) {
                mBtNewBillActivity.setVisibility(View.GONE);
            }
            mProductlist =  entity.getResultObj();
            mItemBillAdapter = new ItemBillAdapter(mBaseActivitySelf,mProductlist);
            mLvMsgActivityHome.setAdapter(mItemBillAdapter);
        }

    }

    @Override
    protected void failed(Throwable throwable) {
        Toast.makeText(mBaseActivitySelf, "网络连接异常,获取账单数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String gerUrl() {
        return "http://101.200.128.107:10028/repayment/order/findOrders";
    }

    @Override
    protected void initView() {
        mLvMsgActivityHome = (ListView) findViewById(R.id.lv_msg_activity_home);

        mImgShoutu = (ImageView) findViewById(R.id.img_shoutu);
        mTvShowActivityHome1 = (TextView) findViewById(R.id.tv_show_activity_home1);
        mTvShowActivityHome2 = (TextView) findViewById(R.id.tv_show_activity_home2);
        mBtNewBillActivity = (LinearLayout) findViewById(R.id.bt_new_bill_activity);
        Log.d("qq", "--------------------------------------"+Constant.getTableuser().getUserNo());
    }

    @Override
    protected void initListener() {
        mBtNewBillActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mBaseActivitySelf, NewBillActivity.class);

                startActivity(intent);
            }
        });



    }


    @Override
    protected boolean isNotUseTitle() {
        return true;
    }
}
