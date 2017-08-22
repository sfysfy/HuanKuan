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
     * resultObj : [{"orderNo":"","userNo":"4","orderType":0,"periodsType":"","periods":0,"monthMoney":0,"bankCard":""}]
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

    public void setTimestamp(int timestamp) {
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
         * orderNo :
         * userNo : 4
         * orderType : 0
         * periodsType :
         * periods : 0
         * monthMoney : 0
         * bankCard :
         */

        private String orderNo;
        private String userNo;
        private int orderType;
        private String periodsType;
        private int periods;
        private int monthMoney;
        private String bankCard;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public String getPeriodsType() {
            return periodsType;
        }

        public void setPeriodsType(String periodsType) {
            this.periodsType = periodsType;
        }

        public int getPeriods() {
            return periods;
        }

        public void setPeriods(int periods) {
            this.periods = periods;
        }

        public int getMonthMoney() {
            return monthMoney;
        }

        public void setMonthMoney(int monthMoney) {
            this.monthMoney = monthMoney;
        }

        public String getBankCard() {
            return bankCard;
        }

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }

        @Override
        public String toString() {
            return "ResultObjBean{" +
                    "orderNo='" + orderNo + '\'' +
                    ", userNo='" + userNo + '\'' +
                    ", orderType=" + orderType +
                    ", periodsType='" + periodsType + '\'' +
                    ", periods=" + periods +
                    ", monthMoney=" + monthMoney +
                    ", bankCard='" + bankCard + '\'' +
                    '}';
        }
    }
}
