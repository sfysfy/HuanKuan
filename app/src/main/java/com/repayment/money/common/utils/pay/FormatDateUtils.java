package com.repayment.money.common.utils.pay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 马彦虎 on 2017/8/24.
 */

public class FormatDateUtils {
    public static String formatDate(long time) {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String format = formatter.format(calendar.getTime());
        return format;
    }
}
