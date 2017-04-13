package com.zykj.onexiubrother.bean;

/**
 * Created by zykj on 2017/4/13.
 */

public class WeiWanChengBean {
    private String ZhongLei;
    private String Address;

    @Override
    public String toString() {
        return "WeiWanChengBean{" +
                "ZhongLei='" + ZhongLei + '\'' +
                ", Address='" + Address + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZhongLei() {
        return ZhongLei;
    }

    public void setZhongLei(String zhongLei) {
        ZhongLei = zhongLei;
    }

    private String Date;
}
