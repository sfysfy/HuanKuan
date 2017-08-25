package com.example.mylibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.R;
import com.example.mylibrary.control.ActivityControl;

public abstract class BaseActivity extends FragmentActivity {

    //封装自己  ,解决内部类上下文等问题
    protected BaseActivity mBaseActivitySelf;
    //渲染器
    protected LayoutInflater mLayoutInflater;
    //构建Fragment
    protected FragmentManager mFragmentManager;

    //标题栏的使用
    private View mViewTitleLeft,mViewTitleCenter,mViewTitleRight;


    //复写onCreate 注意修饰符
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自动添加activity对象
        ActivityControl.addOneActivity(this);

        //去掉原有的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //数据数据出示化操作
        mBaseActivitySelf=this;
        mLayoutInflater=this.getLayoutInflater();
        mFragmentManager=this.getSupportFragmentManager();

        //根的布局文件的 id
        int rootLayoutId=addRootView();
        if (isNotUseTitle()){
            setContentView(rootLayoutId);
        }else{
            //使用标题栏
            addTitleBar(rootLayoutId);
        }

        //三大初始化操作-------------------------------------------------------------------
        initData();
        initView();
        initListener();


    }
    protected boolean isNotUseTitle(){
        return false;  //false带表使用标题栏
    }

    private void addTitleBar(int rootViewId) {
        //统一封装标题栏---------------------------------------------------
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //title  -----------------------------------
        View titleView=mLayoutInflater.inflate(BaseApplication.sLayoutTitle,linearLayout,false);
        mViewTitleLeft=titleView.findViewById(R.id.title_left);
        mViewTitleCenter=titleView.findViewById(R.id.title_center);
        mViewTitleRight=titleView.findViewById(R.id.title_right);
        if (mViewTitleLeft!=null){
            mViewTitleLeft.setVisibility(View.INVISIBLE);
        }
        if (mViewTitleRight!=null){
            mViewTitleRight.setVisibility(View.INVISIBLE);
        }


        //把标题栏控件添加到linearLayout   同时要去掉原有的标题栏
        linearLayout.addView(titleView );
        //title  ------------------------------------

        //实现类的跟布局
        View rootView = mLayoutInflater.inflate(rootViewId, linearLayout, false);
        linearLayout.addView(rootView);
        setContentView(linearLayout);//继承此类的activity都默认是纵向的线性布局

        //统一封装标题栏---------------------------------------------------
    }

    protected void setTitleCenter(String text){
        //只需要控制标题栏上的文本
        if (mViewTitleCenter instanceof View){
            TextView textView= (TextView) mViewTitleCenter;
            textView.setText(text);
        }
    }

    protected void setTitleLeft(String text, View.OnClickListener onClickListener){
        //需要控制文本  和点击时的监听
        if (mViewTitleLeft==null){
            return ;
        }
        //设置该控件显示
        mViewTitleLeft.setVisibility(View.VISIBLE);
        //设置文本
//        if (mViewTitleCenter instanceof View){
//            TextView textView= (TextView) mViewTitleLeft;
//            textView.setText(text);
//        }
        //设置点击时的监听
        mViewTitleLeft.setOnClickListener(onClickListener);
    }

    protected void setViewTitleRight(String text, View.OnClickListener onClickListener){
        //需要控制文本  和点击时的监听
        if (mViewTitleRight==null){
            return ;
        }
        mViewTitleRight.setVisibility(View.VISIBLE);
        //设置文本
        if (mViewTitleCenter instanceof View){
            TextView textView= (TextView) mViewTitleRight;
            textView.setText(text);
        }
        //设置点击时的监听
        mViewTitleRight.setOnClickListener(onClickListener);
    }


    //设置根布局
    protected abstract int addRootView();
    protected abstract void initData();
    protected abstract void initView();
    protected abstract void initListener();

    /*fragment的相关操作------------------------------------------------------------*/
    //添加fragment
    protected  void addFragment(int destId,BaseFragment baseFragment){
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.add(destId,baseFragment,baseFragment.mTag);
        transaction.commit();
    }
    //删除fragment
    protected  void removeFragment(BaseFragment baseFragment){
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.remove(baseFragment);
        transaction.commit();
    }

    //replace
    protected  void replaceFragment(int desId,BaseFragment newFragment){
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.replace(desId,newFragment);
        transaction.commit();
    }
    //hide
    protected  void hideFragment(BaseFragment fragment){
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }
    //show
    protected  void showFragment(BaseFragment fragment){
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }
    /*fragment的相关操作------------------------------------------------------------*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //自动销毁activity对象
        ActivityControl.removeOneActivity(this);
    }
}
