package com.repayment.money.entity;

import java.util.List;

/**
 * Created by 11250 on 2017/8/24.
 */

public class BillDetailEntity {

    /**
     * code : 0
     * timestamp : 0
     * message :
     * resultObj : [{"hkNo":"","orderNo":"","userNo":"","monthMoney":0,"stage":0,"repayMentTime":0,"realRepayMentTime":0,"hkStatus":0,"realPayMoney":0}]
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
         * hkNo :
         * orderNo :
         * userNo :
         * monthMoney : 0
         * stage : 0
         * repayMentTime : 0
         * realRepayMentTime : 0
         * hkStatus : 0
         * realPayMoney : 0
         */

        private String hkNo;
        private String orderNo;
        private String userNo;
        private float monthMoney;
        private int stage;
        private long repayMentTime;
        private long realRepayMentTime;
        private int hkStatus;
        private float realPayMoney;

        public String getHkNo() {
            return hkNo;
        }

        public void setHkNo(String hkNo) {
            this.hkNo = hkNo;
        }

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

        public float getMonthMoney() {
            return monthMoney;
        }

        public void setMonthMoney(int monthMoney) {
            this.monthMoney = monthMoney;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public long getRepayMentTime() {
            return repayMentTime;
        }

        public void setRepayMentTime(int repayMentTime) {
            this.repayMentTime = repayMentTime;
        }

        public long getRealRepayMentTime() {
            return realRepayMentTime;
        }

        public void setRealRepayMentTime(int realRepayMentTime) {
            this.realRepayMentTime = realRepayMentTime;
        }

        public int getHkStatus() {
            return hkStatus;
        }

        public void setHkStatus(int hkStatus) {
            this.hkStatus = hkStatus;
        }

        public float getRealPayMoney() {
            return realPayMoney;
        }

        public void setRealPayMoney(int realPayMoney) {
            this.realPayMoney = realPayMoney;
        }

        @Override
        public String toString() {
            return "ResultObjBean{" +
                    "hkNo='" + hkNo + '\'' +
                    ", orderNo='" + orderNo + '\'' +
                    ", userNo='" + userNo + '\'' +
                    ", monthMoney=" + monthMoney +
                    ", stage=" + stage +
                    ", repayMentTime=" + repayMentTime +
                    ", realRepayMentTime=" + realRepayMentTime +
                    ", hkStatus=" + hkStatus +
                    ", realPayMoney=" + realPayMoney +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BillDetailEntity{" +
                "code=" + code +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", resultObj=" + resultObj +
                '}';
    }
}
