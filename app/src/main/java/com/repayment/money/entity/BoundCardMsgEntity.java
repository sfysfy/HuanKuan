package com.repayment.money.entity;

/**
 * Created by 马彦虎 on 2017/8/27.
 */

public class BoundCardMsgEntity {

    /**
     * ret_code :
     * ret_msg :
     * error_show_mode :
     * correlationID :
     */

    private String ret_code;
    private String ret_msg;
    private String error_show_mode;
    private String correlationID;

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public String getError_show_mode() {
        return error_show_mode;
    }

    public void setError_show_mode(String error_show_mode) {
        this.error_show_mode = error_show_mode;
    }

    public String getCorrelationID() {
        return correlationID;
    }

    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    @Override
    public String toString() {
        return "BoundCardMsgEntity{" +
                "ret_code='" + ret_code + '\'' +
                ", ret_msg='" + ret_msg + '\'' +
                ", error_show_mode='" + error_show_mode + '\'' +
                ", correlationID='" + correlationID + '\'' +
                '}';
    }
}
