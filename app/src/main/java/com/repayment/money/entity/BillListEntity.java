package com.repayment.money.entity;

import java.util.List;

/**
 * Created by 11250 on 2017/8/21.
 */

public class BillListEntity {

    /**
     * code : 0
     * timestamp : 0
     * message :
     * resultObj : [{"orderType":false,"orderNo":"","bankCard":"","userNo":"","periods":0,"bankName":"","periodsType":"","latelyDate":"","monthMoney":0,"latelyDay":0}]
     */

    private int code;
    private long timestamp;
    private String message;
    private List<ResultObjBean> resultObj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultObjBean> getResultObj() {
        return resultObj;
    }

    public void setResultObj(List<ResultObjBean> resultObj) {
        this.resultObj = resultObj;
    }

    public static class ResultObjBean {
        /**
         * orderType : false
         * orderNo :
         * bankCard :
         * userNo :
         * periods : 0
         * bankName :
         * periodsType :
         * latelyDate :
         * monthMoney : 0
         * latelyDay : 0
         */

        private int orderType;
        private String orderNo;
        private String bankCard;
        private String userNo;
        private int periods;
        private String bankName;
        private String periodsType;
        private String latelyDate;
        private float monthMoney;
        private int latelyDay;

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getBankCard() {
            return bankCard;
        }

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public int getPeriods() {
            return periods;
        }

        public void setPeriods(int periods) {
            this.periods = periods;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getPeriodsType() {
            return periodsType;
        }

        public void setPeriodsType(String periodsType) {
            this.periodsType = periodsType;
        }

        public String getLatelyDate() {
            return latelyDate;
        }

        public void setLatelyDate(String latelyDate) {
            this.latelyDate = latelyDate;
        }

        public float getMonthMoney() {
            return monthMoney;
        }

        public void setMonthMoney(int monthMoney) {
            this.monthMoney = monthMoney;
        }

        public int getLatelyDay() {
            return latelyDay;
        }

        public void setLatelyDay(int latelyDay) {
            this.latelyDay = latelyDay;
        }
    }
}
