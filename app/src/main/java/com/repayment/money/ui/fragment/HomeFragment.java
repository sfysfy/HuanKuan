package com.repayment.money.ui.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylibrary.base.BaseFragmentWithNet;
import com.repayment.money.R;
import com.repayment.money.db.TableUser;
import com.repayment.money.entity.BillListEntity;
import com.repayment.money.ui.activity.LogincAtivity;
import com.repayment.money.ui.activity.NewBillActivity;

import org.xutils.ex.DbException;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class HomeFragment extends BaseFragmentWithNet<BillListEntity> {
    private Button mBtLoginLoginActivity;


    @Override
    protected int addRootView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initNetData() {
        TableUser user= null;
        try {
            user = LogincAtivity.mDbManager.selector(TableUser.class).where("phone","=","15731660437").findFirst();
            Log.e("qq", "initNetData: "+user );
        } catch (DbException e) {
            e.printStackTrace();
        }
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
        Log.e("qq", "success: 我成功了----");
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
        mBtLoginLoginActivity = (Button) findViewById(R.id.bt_login_login_activity);

    }

    @Override
    protected void initListener() {
        mBtLoginLoginActivity.setOnClickListener(new View.OnClickListener() {
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