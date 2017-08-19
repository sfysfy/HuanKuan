package com.repayment.money.entity;

/**
 * Created by 11250 on 2017/8/19.
 */

public class SendCode {

    /**
     * code : 0
     * message : string
     * resultObj : {}
     * timestamp : 0
     */

    private int code;
    private String message;
    private ResultObjBean resultObj;
    private int timestamp;

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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultObjBean {
    }
}
