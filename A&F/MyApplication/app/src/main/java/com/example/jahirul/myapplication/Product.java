package com.example.jahirul.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jahirul Bhuiyan on 7/22/2016.
 */
public class Product {
    private String title;
    private String backgroundImage;
    private String promoMessage;
    private String topDescription;
    private String bottomDescription;
    private ArrayList<ProductContent> content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getPromoMessage() {
        return promoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        this.promoMessage = promoMessage;
    }

    public String getTopDescription() {
        return topDescription;
    }

    public void setTopDescription(String topDescription) {
        this.topDescription = topDescription;
    }

    public String getBottomDescription() {
        return bottomDescription;
    }

    public void setBottomDescription(String bottomDescription) {
        this.bottomDescription = bottomDescription;
    }

    public ArrayList<ProductContent> getContent() {
        return content;
    }

    public void setContent(ArrayList<ProductContent> content) {
        this.content = content;
    }

    public Product() {
    }
}
