package com.example.booksapiintents.presenter;

import android.content.Context;
import android.util.Log;

import com.example.booksapiintents.View.ViewContract;
import com.example.booksapiintents.model.BookPojo;
import com.example.booksapiintents.network.BooksApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Presenter implements PresenterContract {
    Context context;
    String bookName, type;
    final String CONST_RESULT = "5";
    String baseUrl;
    BookPojo bookResult;
    BooksApi booksApi;
    ViewContract view;

    public Presenter(ViewContract view) {
        this.view = view;
    }


    private static final String TAG = "Presenter";

    public Presenter(Context context, String bookName, String type, String baseUrl, ViewContract view) {
        this.context = context;
        this.bookName = bookName;
        this.type = type;
        this.baseUrl = baseUrl;
        this.view = view;
    }

    //Todo init retrofit
    //todo send data

    @Override
    public void bindView(ViewContract view) {
        this.view = view;
    }

    public void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        booksApi = retrofit.create(BooksApi.class);


        booksApi.getBooks(bookName, CONST_RESULT, type).enqueue(new Callback<BookPojo>() {
            @Override
            public void onResponse(Call<BookPojo> call, Response<BookPojo> response) {
                //todo send data to the view
                if (response.isSuccessful() && response.body() != null) {
                    view.populateBooks(response.body());
                    bookResult = response.body();
                    Log.d(TAG, "onResponse: " + bookResult.getKind() + ", " + bookResult.getTotalItems());
                }
            }

            @Override
            public void onFailure(Call<BookPojo> call, Throwable throwable) {
                //todo handle error cases
                //bookResult = new BookPojo();
                Log.e(TAG, "onFailure: "+call,throwable);
                view.onError(throwable.getMessage());
            }
        });
    }

    public BookPojo sendBookResult() {
        //Log.d(TAG, "sendBookResult: " + bookResult.getKind() + ", " + bookResult.getTotalItems());
        return bookResult;
    }
}