package com.repayment.money.entity;

/**
 * Created by 马彦虎 on 2017/8/20.
 */

public class CheckBankCardEntity {


    /**
     * code : 0
     * message : string
     * resultObj : {}
     * timestamp : 0
     */

    private int code;
    private String message;
    private String resultObj;

    public String getResultObj() {
        return resultObj;
    }

    public void setResultObj(String resultObj) {
        this.resultObj = resultObj;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

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



    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

//    public static class ResultObjBean {
//
//
//        /**
//         * ret_code : 0000
//         * ret_msg :
//         * sign_type :
//         * sign :
//         * correlationID :
//         * oid_partner :
//         * user_id :
//         * no_order :
//         * token :
//         */
//
//        private String ret_code;
//        private String ret_msg;
//        private String sign_type;
//        private String sign;
//        private String correlationID;
//        private String oid_partner;
//        private String user_id;
//        private String no_order;
//        private String token;
//
//        public String getRet_code() {
//            return ret_code;
//        }
//
//        public void setRet_code(String ret_code) {
//            this.ret_code = ret_code;
//        }
//
//        public String getRet_msg() {
//            return ret_msg;
//        }
//
//        public void setRet_msg(String ret_msg) {
//            this.ret_msg = ret_msg;
//        }
//
//        public String getSign_type() {
//            return sign_type;
//        }
//
//        public void setSign_type(String sign_type) {
//            this.sign_type = sign_type;
//        }
//
//        public String getSign() {
//            return sign;
//        }
//
//        public void setSign(String sign) {
//            this.sign = sign;
//        }
//
//        public String getCorrelationID() {
//            return correlationID;
//        }
//
//        public void setCorrelationID(String correlationID) {
//            this.correlationID = correlationID;
//        }
//
//        public String getOid_partner() {
//            return oid_partner;
//        }
//
//        public void setOid_partner(String oid_partner) {
//            this.oid_partner = oid_partner;
//        }
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getNo_order() {
//            return no_order;
//        }
//
//        public void setNo_order(String no_order) {
//            this.no_order = no_order;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//    }

    @Override
    public String toString() {
        return "CheckBankCardEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", resultObj=" + resultObj +
                ", timestamp=" + timestamp +
                '}';
    }
}
