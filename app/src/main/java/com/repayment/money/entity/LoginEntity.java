package com.repayment.money.entity;

/**
 * Created by 马彦虎 on 2017/8/19.
 */

public class LoginEntity {

    /**
     * code : 0
     * message : string
     * resultObj : {}
     * timestamp : 0
     */

    private int code;
    private String message;
    private ResultObjBean resultObj;
    private long timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", resultObj=" + resultObj +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultObjBean getResultObj() {
        return resultObj;
    }

    public void setResultObj(ResultObjBean resultObj) {
        this.resultObj = resultObj;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultObjBean {

        /**
         * mobile :
         * userNo :
         * name :
         * idCard :
         * password :
         * channel : 0
         */

        private String mobile;
        private String userNo;
        private String name;
        private String idCard;
        private String password;
        private int channel;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }
    }
}
