package com.example.jahirul.myapplication;

import java.io.Serializable;

/**
 * Created by Jahirul Bhuiyan on 7/22/2016.
 */
public class ProductContent implements Serializable {
    private String target;
    private String title;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
