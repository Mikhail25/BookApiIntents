package com.example.booksapiintents.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {
    String selfLink;

    @SerializedName("volumeInfo")
    @Expose
    public BookInfo volumeInfo;


    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public BookInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(BookInfo BookInfo) {
        this.volumeInfo = BookInfo;
    }
}
