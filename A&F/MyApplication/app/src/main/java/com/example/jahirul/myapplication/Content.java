package com.example.jahirul.myapplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jahirul Bhuiyan on 7/22/2016.
 */
public class Content implements Serializable{
    private List<ProductContent> contents;

    public List<ProductContent> getContents() {
        return contents;
    }

    public void setContents(List<ProductContent> contents) {
        this.contents = contents;
    }
}
