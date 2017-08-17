package com.example.mylibrary.control;

import com.example.mylibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马彦虎 on 2017/7/5.
 */

/*
*    解决拿不到activity对象时,也要把他杀掉的问题*/
public class ActivityControl {
    //存储我们程序已经使用过的所有的Activity对象
    // 在baseActivity 的 onCreate  和  onDestroy  实现自动添加和销毁
    private static List<BaseActivity> mBaseActivityControl=new ArrayList<>();

    //添加activity对象
    public static void addOneActivity(BaseActivity baseActivity){
        mBaseActivityControl.add(baseActivity);
    }
    //移除activity对象
    public static void removeOneActivity(BaseActivity baseActivity){
        mBaseActivityControl.remove(baseActivity);
    }

    //销毁全部activitry对象\
    public static void killAll(){
      /*  Iterator<BaseActivity> iterator = mBaseActivityControl.iterator();
        while (iterator.hasNext()) {
            BaseActivity next = iterator.next();
            iterator.remove();
            next.finish();
        }*/

        //倒着移除
        for (int size = mBaseActivityControl.size()-1; size > 0; size--) {
            mBaseActivityControl.get(size).finish();
        }
    }
    //销毁掉指定的Activity      解决拿不到activity对象时,也要把他杀掉的问题   参数传类对象
    public static void killActivity(Class mClass){
        //iterator  不能  用
       /* Iterator<BaseActivity> iterator = mBaseActivityControl.iterator();
        while (iterator.hasNext()) {
            BaseActivity baseActivity = iterator.next();
            if (mClass.equals(baseActivity.getClass())){
//                baseActivity.finish();//回执行onDestroy   执行集合的remove操作  导致删除不干净
            }
        }*/

       //倒着移除
        for (int size = mBaseActivityControl.size()-1; size > 0; size--) {
            if (mClass.equals(mBaseActivityControl.get(size).getClass())){
                mBaseActivityControl.get(size).finish();//
            }
        }
    }

}
