package com.repayment.money.common.utils;

import static com.yintong.secure.e.m.j.br;

/**
 * Created by 11250 on 2017/8/22.
 */

public class BillListUtils {
    public static  String setBillType(String type){
        switch (type){
            case "1":
                return "房贷";
            case "2":
                return "车贷";
        }

        return "";
    }
}
