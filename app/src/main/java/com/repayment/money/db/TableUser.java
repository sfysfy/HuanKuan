package com.repayment.money.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 马彦虎 on 2017/8/19.
 */

@Table(name = "user")
public class TableUser {

    @Column(name = "id", isId = true)
    private int id;


    @Column(name = "phone")
    private String phone;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "userNo")
    private String userNo;


    @Column(name = "channel")
    private int channel;

    @Column(name = "idcard")
    private String idcard;

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "TableUser{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", timestamp=" + timestamp +
                ", userNo='" + userNo + '\'' +
                ", channel=" + channel +
                ", idcard='" + idcard + '\'' +
                '}';
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
