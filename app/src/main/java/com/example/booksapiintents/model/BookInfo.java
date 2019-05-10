package com.example.booksapiintents.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookInfo {

    @SerializedName("authors")
    @Expose
    private List<String> authors = null;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("averageRating")
    @Expose
    private Integer averageRating;

    @SerializedName("imageLinks")
    @Expose
    public BookCover imageLinks;

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }

    public BookCover getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(BookCover imageLinks) {
        this.imageLinks = imageLinks;
    }
}
