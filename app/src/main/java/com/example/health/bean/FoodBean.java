package com.example.health.bean;

import java.io.Serializable;

public class FoodBean implements Serializable {
    private String title;
    private String notmacth;
    private String desc;
    private int picId;

    public FoodBean(String title, String notmacth, String desc, int picId) {
        this.title = title;
        this.notmacth = notmacth;
        this.desc = desc;
        this.picId = picId;
    }

    public FoodBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotmacth() {
        return notmacth;
    }

    public void setNotmacth(String notmacth) {
        this.notmacth = notmacth;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
