package com.example.booksapiintents.View;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksapiintents.Adapters.RecyclerAdapter;
import com.example.booksapiintents.R;
import com.example.booksapiintents.model.BookPojo;
import com.example.booksapiintents.presenter.Presenter;
import com.example.booksapiintents.presenter.PresenterContract;

public class MainActivity extends AppCompatActivity implements ViewContract{
    TextInputLayout tilBookName;
    Spinner spin_bookType;
    Button btnSearch;
    PresenterContract presenter;
    TextView tvKind, tvTotalItems;
    String bookTypeItem;
    RecyclerView recyclerView;

    private static final String BASE_URL = "https://www.googleapis.com/books/";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DataBinding Time!
        spin_bookType = findViewById(R.id.sp_category_type);
        btnSearch = findViewById(R.id.btn_search);
        tvKind = findViewById(R.id.tv_kind_output);
        tvTotalItems = findViewById(R.id.tv_total_items_output);
        tilBookName = findViewById(R.id.til_book_name);
        recyclerView = findViewById(R.id.rv_holder);

        //Initializing Toolbar. Todo must replace with closing toolbar
        Toolbar searchbar = findViewById(R.id.searchBooks);
        setSupportActionBar(searchbar);

        //Populate spinner with options!
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(
                this,R.array.bookType,android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_bookType.setAdapter(spinAdapter);

        spin_bookType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bookTypeItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //button to initNetwork
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Button clicked");
                initNetwork();
            }
        });
    }

    @Override
    public void populateBooks(BookPojo dataSet) {
        Log.d(TAG, "populateBooks: "+dataSet.getKind() + ", " + dataSet.getTotalItems());
        tvKind.setText(dataSet.getKind());
        tvTotalItems.setText(dataSet.getTotalItems());
        Log.d(TAG, "populateBooks: "+dataSet.getItems().get(1).getVolumeInfo().getTitle());

        recyclerView.setAdapter(new RecyclerAdapter(this,dataSet));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onError(String errorMessage) {
        Log.d(TAG, "onError: "+errorMessage);
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();

    }

    public void initNetwork(){
        String bookName = tilBookName.getEditText().getText().toString().trim();
        String bookType = bookTypeItem;

//        Toast.makeText(this,"book name: "+bookName
//        +" Book Type: "+bookType,Toast.LENGTH_SHORT).show();

        Presenter presenter = new Presenter(
                this,
                bookName,
                bookType,
                BASE_URL,MainActivity.this);

        presenter.initRetrofit();
        BookPojo bookPojo = presenter.sendBookResult();


        //Log.d(TAG, "initNetwork: "+dataSet.getKind());

    }
}
