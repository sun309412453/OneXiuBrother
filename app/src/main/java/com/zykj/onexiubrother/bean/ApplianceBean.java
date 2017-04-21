package com.zykj.onexiubrother.bean;

/**
 * Created by zykj on 2017/4/18.
 */

public class ApplianceBean {

    /**
     * id : 9
     * name : 海尔
     */

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ApplianceBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
