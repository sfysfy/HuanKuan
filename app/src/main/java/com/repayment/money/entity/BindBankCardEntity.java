package com.repayment.money.entity;

/**
 * Created by 马彦虎 on 2017/8/21.
 */

public class BindBankCardEntity {

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
    }

    @Override
    public String toString() {
        return "BindBankCardEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", resultObj=" + resultObj +
                ", timestamp=" + timestamp +
                '}';
    }
}
