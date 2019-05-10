package com.example.booksapiintents.View;

import com.example.booksapiintents.model.BookPojo;

public interface ViewContract {
    void populateBooks(BookPojo dataSet);
    void onError(String errorMessage);
}
