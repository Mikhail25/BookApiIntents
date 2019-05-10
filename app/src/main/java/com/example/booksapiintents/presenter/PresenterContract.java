package com.example.booksapiintents.presenter;

import com.example.booksapiintents.View.ViewContract;
import com.example.booksapiintents.model.BookPojo;

public interface PresenterContract {
    void bindView(ViewContract view);
    void initRetrofit();
    BookPojo sendBookResult();
}