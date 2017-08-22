package com.repayment.money.common.utils;

import android.util.Log;

import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.entity.BankCardEntity;
import com.repayment.money.ui.activity.NewBillActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11250 on 2017/8/22.
 */

public class SetBacnkIcon {
    private static NetForJson mNetForJson;
    public static int setBankIcon(final int bankCard){

       mNetForJson=new NetForJson("http://101.200.128.107:10028/repayment/bank/bankCardBin",new NetOverListener<BankCardEntity>() {

           @Override
           public void success(BankCardEntity bankCardEntity) {
                   for (int i = 0; i < bankBin.size(); i++) {
                       if (bankBin.get(i).equals( bankCardEntity.getResultObj().getBank_code())) {

                       }
                   }
           }

           @Override
           public void failed(Throwable throwable) {
               NewBillActivity.isyzBankCard = false;
               EventBus.getDefault().post(3);
           }

           @Override
           public void onCancel() {

           }

           @Override
           public void onFinish() {

           }
       });
//        mNetForJson.setClassEntity(BankCardEntity.class);
        mNetForJson.addParam("bankCard", bankCard);
        mNetForJson.execute();
        return 0;
    }
    private static List<String> bankBin = new ArrayList<String>() {
        {
            add("01020000");
            add("01030000");
            add("01040000");
            add("01050000");
            add("03010000");
            add("03020000");
            add("03030000");
            add("03040000");
            add("03050000");
            add("03060000");
            add("03070000");
            add("03080000");
            add("03090000");
            add("03100000");
            add("03110000");
            add("03133930");
            add("03134500");
            add("03134560");
            add("03134650");
            add("03160000");
            add("03170000");
            add("03200000");
            add("03280000");
            add("04012900");
            add("04023930");
            add("04031000");
            add("04083320");
            add("04115010");
            add("04123330");
            add("04145210");
            add("04184930");
            add("04202220");
            add("04233310");
            add("04243010");
            add("04256020");
            add("04263380");
            add("04270001");
            add("04302240");
            add("04332350");
            add("04341100");
            add("04354910");
            add("04360010");
            add("04375850");
            add("04403600");
            add("04416900");
            add("04422610");
            add("04437010");
            add("04447910");
            add("04478210");
            add("04491610");
            add("04504520");
            add("04571260");
            add("04593450");
            add("04615510");
            add("04634280");
            add("04643970");
            add("04652280");
            add("04672290");
            add("04703350");
            add("04733450");
            add("04761430");
            add("04786110");
            add("04791920");
            add("04836560");
            add("04856590");
            add("04866570");
            add("04885050");
            add("04901380");
            add("04922600");
            add("04956140");
            add("04966730");
            add("04986580");
            add("04991240");
            add("05027360");
            add("05031680");
            add("05083000");
            add("05131410");
            add("05167030");
            add("05171270");
            add("05247410");
            add("05253450");
            add("05274550");
            add("05284630");
            add("05303380");
            add("05311930");
            add("05326560");
            add("05354970");
            add("05365030");
            add("05374610");
            add("05406650");
            add("05417900");
            add("05417930");
            add("05426900");
            add("05438720");
            add("05478820");
            add("05484950");
            add("05492340");
            add("05516620");
            add("05521340");
            add("05565040");
            add("05591750");
            add("05611480");
            add("05625080");
            add("05646710");
            add("05705500");
            add("05755200");
            add("05785800");
            add("05803320");
            add("05818200");
            add("05824540");
            add("14033055");
            add("14045840");
            add("14055810");
            add("14097310");
            add("14105200");
            add("14123020");
            add("14136530");
            add("14144500");
            add("14144520");
            add("14156020");
            add("14163056");
            add("14181000");
            add("14283054");
            add("14293300");
            add("14303050");
            add("14333051");
            add("14341770");
            add("14367000");
            add("14385500");
            add("14404900");
            add("14411200");
            add("14427900");
            add("14436100");
            add("14448800");
            add("14452400");
            add("14468700");
            add("14473600");
            add("14486400");
            add("14498500");
            add("14505800");
            add("14511900");
            add("14526500");
            add("14538200");
            add("14542200");
            add("14551600");
            add("14572600");
            add("14595210");
            add("14603040");
            add("15036512");
            add("15136900");
            add("15206900");
            add("15947916");
            add("17219924");
            add("64094510");
            add("64135810");
            add("64221210");
            add("64296510");
            add("64314730");
            add("04375850");
            add("64384530");
            add("64392270");
            add("64544240");
            add("64554770");
            add("64588510");
            add("64624580");
            add("64667310");
            add("64733450");
            add("64741910");
            add("64786110");
            add("64895910");
            add("64916170");
            add("65012900");
            add("65085883");
            add("65097300");
            add("65131410");
            add("65154680");
            add("65173900");
            add("65191100");
            add("65226510");
            add("65243000");
            add("65264330");
            add("65274550");
            add("65394200");
            add("65675060");
        }
    };
}
