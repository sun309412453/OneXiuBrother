package com.zykj.onexiubrother.bean;

/**
 * Created by zykj on 2017/4/13.
 */

public class WeiWanChengBean {
  private String Zhuangtai,Zhonglei,date,Add;

    @Override
    public String toString() {
        return "WeiWanChengBean{" +
                "Zhuangtai='" + Zhuangtai + '\'' +
                ", Zhonglei='" + Zhonglei + '\'' +
                ", date='" + date + '\'' +
                ", Add='" + Add + '\'' +
                '}';
    }

    public String getZhuangtai() {
        return Zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        Zhuangtai = zhuangtai;
    }

    public String getZhonglei() {
        return Zhonglei;
    }

    public void setZhonglei(String zhonglei) {
        Zhonglei = zhonglei;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdd() {
        return Add;
    }

    public void setAdd(String add) {
        Add = add;
    }
}
