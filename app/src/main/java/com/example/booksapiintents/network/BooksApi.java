package com.example.booksapiintents.network;

import com.example.booksapiintents.model.BookPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface BooksApi {

    @GET("v1/volumes")
    Call<BookPojo> getBooks(@Query("q") String bookName,
                            @Query("maxResults") String max,
                            @Query("printType") String type);

    //https://www.googleapis.com/books/

    //
}
