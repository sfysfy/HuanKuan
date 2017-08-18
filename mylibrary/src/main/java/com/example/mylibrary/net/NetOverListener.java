package com.example.mylibrary.net;

/**
 * Created by 马彦虎 on 2017/6/27.
 */

public abstract class NetOverListener<T> {
    public abstract void success( T t);
    public abstract void failed(Throwable throwable);
    public abstract void onCancel();
    public abstract void onFinish();

}
