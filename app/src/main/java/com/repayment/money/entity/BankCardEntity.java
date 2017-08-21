package com.repayment.money.entity;

/**
 * Created by 11250 on 2017/8/21.
 */

public class BankCardEntity {

    /**
     * code : 0
     * timestamp : 0
     * message :
     * resultObj : {"bank_code":"","bank_name":"","card_type":""}
     */

    private int code;
    private long timestamp;
    private String message;
    private ResultObjBean resultObj;

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

    public ResultObjBean getResultObj() {
        return resultObj;
    }

    public void setResultObj(ResultObjBean resultObj) {
        this.resultObj = resultObj;
    }


    public static class ResultObjBean {
        /**
         * bank_code :
         * bank_name :
         * card_type :
         */

        private String bank_code;
        private String bank_name;
        private String card_type;

        public String getBank_code() {
            return bank_code;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        @Override
        public String toString() {
            return "ResultObjBean{" +
                    "bank_code='" + bank_code + '\'' +
                    ", bank_name='" + bank_name + '\'' +
                    ", card_type='" + card_type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BankCardEntity{" +
                "code=" + code +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", resultObj=" + resultObj +
                '}';
    }
}
