package com.repayment.money.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.base.BaseFragment;
import com.repayment.money.R;
import com.repayment.money.ui.fragment.HomeFragment;
import com.repayment.money.ui.fragment.UserCenterFragment;

public class MainActivity extends BaseActivity {
    private ImageView mImgBillActivityMain;
    private TextView mTvBillActivityMain;
    private ImageView mImgUserActivityMain;
    private TextView mTvUserActivityMain;

    private RelativeLayout mActivityMain;
    private FrameLayout mLayoutFragActivityMain;
    private LinearLayout mTabBillActivityMain;
    private LinearLayout mTabUsercenterActivityMain;


    private HomeFragment mHomeFragment=new HomeFragment();
    private UserCenterFragment mUserCenterFragment=new UserCenterFragment();
    private BaseFragment[] mFragments={mHomeFragment,mUserCenterFragment};


    @Override
    protected int addRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

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

        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
        mLayoutFragActivityMain = (FrameLayout) findViewById(R.id.layout_frag_activity_main);
        mTabBillActivityMain = (LinearLayout) findViewById(R.id.tab_bill_activity_main);
        mTabUsercenterActivityMain = (LinearLayout) findViewById(R.id.tab_usercenter_activity_main);

        mImgBillActivityMain = (ImageView) findViewById(R.id.img_bill_activity_main);
        mTvBillActivityMain = (TextView) findViewById(R.id.tv_bill_activity_main);
        mImgUserActivityMain = (ImageView) findViewById(R.id.img_user_activity_main);
        mTvUserActivityMain = (TextView) findViewById(R.id.tv_user_activity_main);



        changeFrag(mHomeFragment);
        mImgBillActivityMain.setImageResource(R.drawable.billy);
        mTvBillActivityMain.setTextColor(Color.rgb(253,206,0));
        mImgUserActivityMain.setImageResource(R.drawable.usercenterw);
        mTvUserActivityMain.setTextColor(Color.rgb(255,255,255));
        setTitleCenter("还款王");
    }

    @Override
    protected void initListener() {

        mTabBillActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFrag(mHomeFragment);
                mImgBillActivityMain.setImageResource(R.drawable.billy);
                mTvBillActivityMain.setTextColor(Color.rgb(253,206,0));
                mImgUserActivityMain.setImageResource(R.drawable.usercenterw);
                mTvUserActivityMain.setTextColor(Color.rgb(255,255,255));
                setTitleCenter("还款王");

            }
        });
        mTabUsercenterActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFrag(mUserCenterFragment);
                mImgBillActivityMain.setImageResource(R.drawable.billw);
                mTvBillActivityMain.setTextColor(Color.rgb(255,255,255));
                mImgUserActivityMain.setImageResource(R.drawable.usercentery);
                mTvUserActivityMain.setTextColor(Color.rgb(253,206,0));
                setTitleCenter("个人中心");

            }
        });

    }


    private void changeFrag(BaseFragment baseFragmentNow) {

        for (BaseFragment fragment : mFragments) {
            if (fragment!=baseFragmentNow) {
                hideFragment(fragment);
                continue;
            }
        }

        if (!baseFragmentNow.isAdded()) {
            addFragment(R.id.layout_frag_activity_main,baseFragmentNow);
        }
        showFragment(baseFragmentNow);
    }

}