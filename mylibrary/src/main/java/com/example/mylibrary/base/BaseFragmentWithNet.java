package com.example.mylibrary.base;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 马彦虎 on 2017/6/28.
 */

public abstract class BaseFragmentWithNet<T> extends BaseFragment {
    //网络访问的工具类
    protected NetForJson mNetForJson;
    protected String mUrl;
    private NetOverListener mNetOverListener=new NetOverListener<T>() {
        @Override
        public void success(T entity) {
            BaseFragmentWithNet.this.success(entity);
        }
        @Override
        public void failed(Throwable throwable) {
            BaseFragmentWithNet.this.failed(throwable);
        }
        @Override
        public void onCancel() {
            BaseFragmentWithNet.this.onCancel();
        }
        @Override
        public void onFinish() {
            BaseFragmentWithNet.this.onFinish();
        }
    };

    @Override
    protected void initData() {
        ParameterizedType parameterizedType = (ParameterizedType) this//this指的是实现类的对象!!!!!
                .getClass().getGenericSuperclass();
        Type mClassEntity = parameterizedType.getActualTypeArguments()[0];

        //实例化变量
        mNetForJson=new NetForJson(gerUrl(),mNetOverListener);
        //设置泛型的
        mNetForJson.setClassEntity(mClassEntity);

        initLocalData();//处理本地的数据
        initNetData();//处理网络的响应数据
    }
    /* //要实现网络访问必须使用有参数的构造器   但是activity不是我们new的顾方式不通
    public BaseActivityWithNet(String url, NetOverListener netOverListener) {
        mUrl = url;
        mNetOverListener = netOverListener;
    }*/
    //net访问带参数的方法
    public void addParam(String key,String value){
        mNetForJson.addParam(key,value);
    }
    protected void execute(){
        //执行网络访问的方法
        mNetForJson.execute();
    }


    protected abstract void initNetData();
    protected abstract void initLocalData();
    protected abstract void success(T entity);
    protected abstract void failed(Throwable throwable);
    //由实现类提供url
    protected abstract String gerUrl();

    protected  void onCancel(){};

    protected  void onFinish(){};
}

