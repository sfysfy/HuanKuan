package com.repayment.money.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.net.NetForJson;
import com.example.mylibrary.net.NetOverListener;
import com.repayment.money.R;
import com.repayment.money.common.Constant;
import com.repayment.money.common.utils.BankNameUtil;
import com.repayment.money.common.utils.IconUtil;
import com.repayment.money.common.utils.StringType2NumType;
import com.repayment.money.db.TableUser;
import com.repayment.money.entity.BankCardEntity;
import com.repayment.money.entity.BankCardListItemEntity;
import com.repayment.money.entity.NewBillEntity;
import com.repayment.money.ui.views.ChangeCardPopupWindow;
import com.repayment.money.ui.views.RepayPopupWindow;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.repayment.money.common.Constant.BASE_URL_NEWBILL;

public class NewBillActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEdtRepayNumber;
    private EditText mEdtRepayNewbill;
    private EditText mEdtBankNewbill;
    private EditText mEdtCardNewbill;
    private ImageView mImgAddNewbill;
    private TextView mTvBankinfoNewbill;
    private ImageView mImgMoreNewbill;
    private Button mBtAddNewbill;
    private LinearLayout mXzNewbankBill;

    private List<String> mRepayType = new ArrayList<>();
    private List<String> mRepayDate = new ArrayList<>();
    private LinearLayout mLvTypeNewbill;
    private EditText mEdtTypeNewbill;
    private LinearLayout mLvDateNewbill;
    private EditText mEdtDateNewbill;

    private ArrayAdapter<String> mDataAdapter;
    private ArrayAdapter<String> mTypeAdapter;
    private ListPopupWindow mDataPop;
    private ListPopupWindow mTypePop;


    private NetForJson mNetForCardJson;
    private NetForJson mNetForBillJson;
    private NetForJson mNetForCardListJson;

    public static final int NET_CARD = 1112;
    public static final int NET_BILL = 120;
    public static final int NET_LISTCARD = 130;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case NET_LISTCARD:
                    mNetForCardListJson.execute();
                    break;
                case NET_CARD:
                    mNetForCardJson.execute();
                    break;
                case NET_BILL:
                    mNetForBillJson.execute();
                    break;
            }
        }
    };

    @Override
    protected int addRootView() {

        return R.layout.activity_new_bill;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        setTitleCenter("新增账单");
        setTitleLeft("", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusBarColor));
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }

        mLvTypeNewbill = (LinearLayout) findViewById(R.id.lv_type_newbill);
        mEdtTypeNewbill = (EditText) findViewById(R.id.edt_type_newbill);
        mLvDateNewbill = (LinearLayout) findViewById(R.id.lv_date_newbill);
        mEdtDateNewbill = (EditText) findViewById(R.id.edt_date_newbill);



        mEdtRepayNumber = (EditText) findViewById(R.id.edt_repay_number);
        mEdtRepayNewbill = (EditText) findViewById(R.id.edt_repay_newbill);
        mEdtBankNewbill = (EditText) findViewById(R.id.edt_bank_newbill);
        mEdtCardNewbill = (EditText) findViewById(R.id.edt_card_newbill);
        mImgAddNewbill = (ImageView) findViewById(R.id.img_add_newbill);
        mTvBankinfoNewbill = (TextView) findViewById(R.id.tv_bankinfo_newbill);
        mImgMoreNewbill = (ImageView) findViewById(R.id.img_more_newbill);
        mBtAddNewbill = (Button) findViewById(R.id.bt_add_newbill);
        mXzNewbankBill = (LinearLayout) findViewById(R.id.xz_newbank_bill);

        mRepayType.add("房贷");
        mRepayType.add("车贷");
        mRepayType.add("现金贷");
        mRepayType.add("花呗");
        mRepayType.add("京东白条");

        mTypeAdapter=new ArrayAdapter<String>(mBaseActivitySelf,android.R.layout.simple_selectable_list_item,mRepayType);
        mTypePop=new ListPopupWindow(mBaseActivitySelf);
        mTypePop.setHeight(800);
        mTypePop.setWidth(500);
        mTypePop.setAdapter(mTypeAdapter);
        mTypePop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mEdtTypeNewbill.setText(mRepayType.get(i));
                mTypePop.dismiss();
            }
        });

        for (int i = 0; i < 31; i++) {
            mRepayDate.add(i + 1 + "");
        }
        mDataAdapter=new ArrayAdapter<String>(mBaseActivitySelf,android.R.layout.simple_selectable_list_item,mRepayDate);
        mDataPop =new ListPopupWindow(mBaseActivitySelf);
        mDataPop.setHeight(800);
        mDataPop.setWidth(170);
        mDataPop.setAdapter(mDataAdapter);
        mDataPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mEdtDateNewbill.setText(i+1+"");
                mDataPop.dismiss();
            }
        });


        mNetForCardListJson=new NetForJson("http://101.200.128.107:10028/repayment/bank/findBankList",new NetForCardlist());
        mNetForCardListJson.addParam("userNo", Constant.getTableuser().getUserNo());
        mHandler.sendEmptyMessage(NET_LISTCARD);


        mLvTypeNewbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTypePop.setAnchorView(view);
                mTypePop.show();
            }
        });

        mLvDateNewbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataPop.setAnchorView(view);
                mDataPop.show();
            }
        });

    }

    @Override
    protected void initListener() {
        mBtAddNewbill.setOnClickListener(this);
        mXzNewbankBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeCardPopupWindow changeCardPopupWindow = new ChangeCardPopupWindow(mBaseActivitySelf,mBankCardListItemEntity);
                changeCardPopupWindow.setFkCard(mTvBankinfoNewbill,mImgAddNewbill);
                changeCardPopupWindow.showPopupWindow(findViewById(R.id.xz_newbank_bill));
            }
        });
      mEdtBankNewbill.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              boolean empty = mEdtCardNewbill.getText().toString().isEmpty();
              if (!mEdtCardNewbill.getText().toString().isEmpty()) {
                 doNetCard();

              }else{
                  Toast.makeText(mBaseActivitySelf, "请先输入银行卡号", Toast.LENGTH_SHORT).show();
              }
          }
      });

    }
    private int changeCard=0;

    TableUser user = Constant.getTableuser();
    String userNo;
    String orderType;
    String periodesType;
    String periodes;
    String monthMoney;
    String hkDay;
    String bankCard;
    String bankName;

    @Override
    public void onClick(View view) {

        if (user != null) {
            userNo = Constant.getTableuser().getUserNo();
//            orderType = "0";//---->暂时只有房贷
            orderType = StringType2NumType.stringType2NumType(mEdtTypeNewbill.getText().toString());
            periodesType = "M";
            periodes = mEdtRepayNumber.getText().toString();
            monthMoney = mEdtRepayNewbill.getText().toString();
            hkDay = mEdtDateNewbill.getText().toString();
            bankCard = mEdtCardNewbill.getText().toString();
            bankName=mEdtBankNewbill.getText().toString();
            Log.e("qq", "bankName"+bankName );
            //内容是否有空
            boolean isEmpty = !(orderType.isEmpty() || periodesType.isEmpty() || periodes.isEmpty() || monthMoney.isEmpty() || hkDay.isEmpty() || bankCard.isEmpty()||bankName.isEmpty());
            if (isEmpty) {
                String hkbankCard = mBankCardListItemEntity.getResultObj().get(changeCard).getBankCard();
                String fkbankCard =mBankCardListItemEntity.getResultObj().get(RepayPopupWindow.mChageBankR).getBankCard();
                Log.e("qq", "onClick: 付款卡"+fkbankCard+"还款卡"+hkbankCard );
                if (!hkbankCard.equals(fkbankCard)){
                    doNetBill();
                }else{
                    Toast.makeText(mBaseActivitySelf, "还款卡和付款卡不能相同,请重新选择", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(mBaseActivitySelf, "内容不可为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mBaseActivitySelf, "请先登录账户", Toast.LENGTH_SHORT).show();
        }
    }

    public void doNetCard() {
        mNetForCardJson = new NetForJson("http://101.200.128.107:10028/repayment/bank/bankCardBin", new NetForCard());
        bankCard=mEdtCardNewbill.getText().toString();
        Log.d("qq", "----------------------------------------------------------"+bankCard);
        mNetForCardJson.addParam("bankCard", bankCard);
        mHandler.sendEmptyMessage(NET_CARD);
    }

    public void doNetBill() {
        mNetForBillJson = new NetForJson(BASE_URL_NEWBILL, new NetForNewBill());
        mNetForBillJson.addParam("userNo", userNo);
        mNetForBillJson.addParam("orderType", orderType);
        mNetForBillJson.addParam("periodsType", periodesType);
        mNetForBillJson.addParam("periods", periodes);
        mNetForBillJson.addParam("monthMoney", monthMoney);
        mNetForBillJson.addParam("hkDay", hkDay);
        mNetForBillJson.addParam("bankCard", bankCard);
        mNetForBillJson.addParam("bankName", bankName);
        mHandler.sendEmptyMessage(NET_BILL);
    }

    private class NetForCard extends NetOverListener<BankCardEntity> {

        @Override
        public void success(BankCardEntity bankCardEntity) {
            Log.e("qq", "success: "+bankCardEntity );
            if (bankCardEntity.getCode() == 1) {
                for (int i = 0; i < bankBin.size(); i++) {
                    if (bankBin.get(i).equals(bankCardEntity.getResultObj().getBank_code())) {
                        bankCard=bankCardEntity.getResultObj().getBank_name();
                        mEdtBankNewbill.setText(bankCard);
                        return;
                    }
                }
            } else {
                Toast.makeText(mBaseActivitySelf, "不支持该银行卡", Toast.LENGTH_SHORT).show();
                mEdtBankNewbill.setText("");

            }

        }

        @Override
        public void failed(Throwable throwable) {
            Toast.makeText(mBaseActivitySelf, "网络访问失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }
    }
    private BankCardListItemEntity mBankCardListItemEntity;
    private class NetForCardlist extends NetOverListener<BankCardListItemEntity>{

        @Override
        public void success(BankCardListItemEntity bankCardListItemEntity) {
            mBankCardListItemEntity=bankCardListItemEntity;
            String bankName = bankCardListItemEntity.getResultObj().get(RepayPopupWindow.mChageBankR).getBankName();
            String bankCard = bankCardListItemEntity.getResultObj().get(RepayPopupWindow.mChageBankR).getBankCard();
            mTvBankinfoNewbill.setText(BankNameUtil.bankNameFormat(bankName,bankCard));
            mImgAddNewbill.setImageResource(IconUtil.getIcon(bankName));
        }

        @Override
        public void failed(Throwable throwable) {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }
    }

    private class NetForNewBill extends NetOverListener<NewBillEntity> {

        @Override
        public void success(NewBillEntity newBillEntity) {
            if (newBillEntity.getCode() == 1) {
                Toast.makeText(mBaseActivitySelf, "添加成功", Toast.LENGTH_SHORT).show();
                NewBillActivity.this.finish();
                EventBus.getDefault().post("刷新");
            } else {
                Toast.makeText(mBaseActivitySelf, "添加失败,请检查输入信息", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void failed(Throwable throwable) {
            Log.d("qq", "NewBill失败了");
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }
    }

    private List<String> bankBin = new ArrayList<String>() {
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
