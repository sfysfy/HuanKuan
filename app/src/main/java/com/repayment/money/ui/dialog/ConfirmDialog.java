package com.repayment.money.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.repayment.money.R;

/**
 * Created by 马彦虎 on 2017/8/22.
 */

public class ConfirmDialog extends Dialog{
    private TextView mTvDigConfirm;
    private Button mBtDoDigConfirm;
    private Button mBtCancelDigConfirm;
    private String mBtConfirmText;
    private String mBtCancelText;



    public ConfirmDialog(@NonNull Context context,String btConfirmText,String cancelText) {
        super(context);
        this.getWindow().setLayout(550,300);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.confirm_dig);
        mBtConfirmText=btConfirmText;
        mBtCancelText=cancelText;
        mTvDigConfirm = (TextView) findViewById(R.id.tv_dig_confirm);
        mBtDoDigConfirm = (Button) findViewById(R.id.bt_do_dig_confirm);
        mBtCancelDigConfirm = (Button) findViewById(R.id.bt_cancel_dig_confirm);

        mBtDoDigConfirm.setText(mBtConfirmText);
        mBtCancelDigConfirm.setText(mBtCancelText);

        mBtCancelDigConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }

    public void setBtConfirmClickListener(View.OnClickListener onClickListener){
        mBtDoDigConfirm.setOnClickListener(onClickListener);
    }
}
