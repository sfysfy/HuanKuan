package com.example.mylibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.R;

/**
 * Created by 马彦虎 on 2017/6/28.
 */

public abstract class BaseFragment extends Fragment {
    protected String mTag;//fragment的唯一标记
    //封装自己  ,解决内部类上下文等问题
    protected BaseActivity mBaseActivitySelf;
    //渲染器
    protected LayoutInflater mLayoutInflater;
    //构建Fragment
    protected FragmentManager mFragmentManager;
    //标题栏的使用
    private View mViewTitleLeft,mViewTitleCenter,mViewTitleRight;
    //根布局文件的设置
    protected View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //赋值
        mTag=this.getClass().getSimpleName()+this.hashCode();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //数据数据出示化操作
        mBaseActivitySelf= (BaseActivity) this.getActivity();
        mLayoutInflater=inflater;
        mFragmentManager=this.getFragmentManager();

        //根的布局文件的 id
        int rootLayoutId=addRootView();


        if (isNotUseTitle()) {
            //手动渲染出跟布局
            mRootView=mLayoutInflater.inflate(rootLayoutId,container,false);
        }else{
            //如果使用标题栏  则在addTitleBar中修改mRootView 的值
            addTitleBar(rootLayoutId);
        }



        //三大初始化操作-------------------------------------------------------------------
        initData();
        initView();
        initListener();

        return mRootView;
    }

    protected View findViewById(int resId){
        return mRootView.findViewById(resId);
    }


    protected boolean isNotUseTitle(){
        return false;  //false带表使用标题栏
    }

    private void addTitleBar(int rootViewId) {
        //统一封装标题栏---------------------------------------------------
        LinearLayout linearLayout=new LinearLayout(mBaseActivitySelf);
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
        linearLayout.addView(titleView);
        //title  ------------------------------------

        //实现类的跟布局
        View rootView = mLayoutInflater.inflate(rootViewId, linearLayout, false);
        linearLayout.addView(rootView);
        //如过添加title 修改rootView
        mRootView=linearLayout;
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
        if (mViewTitleCenter instanceof View){
            TextView textView= (TextView) mViewTitleLeft;
            textView.setText(text);
        }
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
    protected  void showFragment(int desId,BaseFragment fragment){
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }
    /*fragment的相关操作------------------------------------------------------------*/
}

