package com.repayment.money.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

import com.repayment.money.R;

/**
 * Created by 马彦虎 on 2017/8/22.
 */

public class HintDiglog extends Dialog{



    public HintDiglog(@NonNull Context context) {
        super(context);
        this.getWindow().setLayout(550,300);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.hint_dig);

        setCancelable(false);
    }


}
