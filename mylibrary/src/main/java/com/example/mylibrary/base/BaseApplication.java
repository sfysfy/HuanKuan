package com.example.mylibrary.base;

import android.app.Application;
import android.content.Context;

import com.example.mylibrary.util.Utils;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by 马彦虎 on 2017/6/28.
 */

public abstract class BaseApplication extends Application {
    //此静态变量用来存放  标题栏的布局文件
    public static int sLayoutTitle;
    //全局上下文
    public  static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //全局的共性的初始化操作  xUtils
        initX();
        //非共性的出示化
        initOthets();
        //初始化全局的title   实现由子类返回布局文件的Id
        sLayoutTitle= initTitleBarId();
        mContext=this;

        //初始化工具类
        initUtils();

    }

    private void initUtils() {
        Utils.init(this);//为工具类提供上下文
    }


    protected abstract int initTitleBarId();


    //做各自的初始化操作
    protected abstract void initOthets();

    protected  void initX(){
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    };

}
