package com.wangliangjun.androidtraining133.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ChooseConstellationList{

    /**
     * id : 1
     * name : 白羊座
     * img : /img/constellation/baiyang_icon.png
     * date : （阳历3.21-4.19）
     */

    private int id;
    private String name;
    private String img;
    private String date;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
