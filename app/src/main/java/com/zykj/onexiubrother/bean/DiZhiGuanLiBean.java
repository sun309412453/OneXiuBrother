package com.zykj.onexiubrother.bean;

/**
 * Created by zykj on 2017/4/15.
 */

public class DiZhiGuanLiBean {
    private String name;
    private String phone;
    private String add;

    @Override
    public String toString() {
        return "DiZhiGuanLiBean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", add='" + add + '\'' +
                ", zhuangtai='" + zhuangtai + '\'' +
                '}';
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String zhuangtai ;
}
