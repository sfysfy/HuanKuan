package com.example.mylibrary.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by 马彦虎 on 2017/6/27.
 */

public class NetForJson implements Callback.CommonCallback<String> {
    //不确定的通过构造器传参数
    private String mUrl;//url
    private NetForJsonMethod mNetForJsonMethod= NetForJsonMethod.GET;//访问方式
    private Type mClassEntity;//json解析时的操作
    private NetOverListener mNetOverListener;//访问结束后成功或者失败的监

    //xUtils访问
    RequestParams mRequestParams;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NET_OK:
                    mNetOverListener.success(msg.obj);
                    break;
                case NET_ERR:
                    mNetOverListener.failed((Throwable) msg.obj);
                    break;
            }
        }
    };
    public static final int NET_OK=110;
    public static final int NET_ERR=120;

    private Cancelable mCancelable;


    public void setClassEntity(Type classEntity) {
        mClassEntity = classEntity;
    }

    public NetForJson(String url, NetForJsonMethod netForJsonMethod, NetOverListener netOverListener) {
        mUrl = url;
        mNetForJsonMethod = netForJsonMethod;
        mNetOverListener = netOverListener;
        mRequestParams=new RequestParams(mUrl);
        //根据泛型获取Type类型，从而省略传入解析类型参数
        if (netOverListener!=null){
            ParameterizedType parameterizedType = (ParameterizedType) netOverListener
                    .getClass().getGenericSuperclass();
            mClassEntity = parameterizedType.getActualTypeArguments()[0];
        }

    }

    public NetForJson(String url, NetOverListener netOverListener) {
        this(url, NetForJsonMethod.POST,netOverListener);
    }


    public  void execute(){
        if (mNetForJsonMethod== NetForJsonMethod.POST){
            doPost();
        }else{
            doGet();
        }
    }

    private void doGet() {
        //发起get访问 this是让本类实现回调接口
        mCancelable = x.http().get(mRequestParams,this);
        Log.e("qq", "doPost: 执行了get访问");
    }

    private void doPost() {
        //post访问
        mCancelable=x.http().post(mRequestParams,this);
        Log.e("qq", "doPost: 执行了POST访问");
    }
   //取消网络访问
    public void Cancel(){
       if(!mCancelable.isCancelled()){
           mCancelable.cancel();
       }
    }
    //网络访问拼接参数的方法
    public void addParam(String key,Object value){
        if (mNetForJsonMethod==NetForJsonMethod.GET){
            mRequestParams.addParameter(key,value);
        }else {
            mRequestParams.addBodyParameter(key,value+"");
        }
    }

    @Override
    public void onSuccess(String result) {
        //访问成功是做解析
        Gson gson=new Gson();
        Object fromJson = gson.fromJson(result, mClassEntity);
        //做成功的响应操作
        mNetOverListener.success(fromJson);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //访问失败做失败操作
        ex.printStackTrace();
        mNetOverListener.failed(ex);
    }

    @Override
    public void onCancelled(CancelledException cex) {
        mNetOverListener.onCancel();
    }

    @Override
    public void onFinished() {
        mNetOverListener.onFinish();
    }
}
