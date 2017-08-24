package com.repayment.money.entity;

/**
 * Created by 11250 on 2017/8/24.
 */

public class RepayEntity {

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

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultObjBean {
    }
}
