package com.repayment.money.entity;

import java.util.List;

/**
 * Created by 马彦虎 on 2017/8/23.
 */

public class BankCardListItemEntity {

    /**
     * code : 0
     * timestamp : 0
     * message :
     * resultObj : [{"userNo":"","bankCard":"","bankName":"","mobile":"","noAgree":"","cardType":0}]
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

    @Override
    public String toString() {
        return "BankCardListItemEntity{" +
                "code=" + code +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", resultObj=" + resultObj +
                '}';
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
        @Override
        public String toString() {
            return "ResultObjBean{" +
                    "userNo='" + userNo + '\'' +
                    ", bankCard='" + bankCard + '\'' +
                    ", bankName='" + bankName + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", noAgree='" + noAgree + '\'' +
                    ", cardType=" + cardType +
                    '}';
        }

        /**
         * userNo :
         * bankCard :
         * bankName :
         * mobile :
         * noAgree :
         * cardType : 0
         */

        private String userNo;
        private String bankCard;
        private String bankName;
        private String mobile;
        private String noAgree;
        private int cardType;

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getBankCard() {
            return bankCard;
        }

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNoAgree() {
            return noAgree;
        }

        public void setNoAgree(String noAgree) {
            this.noAgree = noAgree;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }
    }
}
