package com.example.booksapiintents.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksapiintents.R;
import com.example.booksapiintents.model.BookPojo;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {
    private Context context;
    BookPojo dataSet;


    public RecyclerAdapter(Context context, BookPojo dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from
                (viewGroup.getContext()).inflate
                (R.layout.book_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {

        customViewHolder.bookTitle.setText(dataSet.getItems().get(i).getVolumeInfo().getTitle());
        customViewHolder.language.setText(dataSet.getItems().get(i).getVolumeInfo().getLanguage());

        if(dataSet.getItems().get(i).getVolumeInfo().getAverageRating() != null) {
            customViewHolder.numRating.setText(dataSet.getItems().get(i).getVolumeInfo().getAverageRating().toString());
        }else{
            customViewHolder.numRating.setText("N/A");
        }
        customViewHolder.authors.setText(Arrays.toString(dataSet.getItems().get(i).getVolumeInfo().getAuthors()
                .toArray()).replaceAll("\\[|\\]", ""));

        Picasso.get().load(dataSet.getItems().get(i).getVolumeInfo().getImageLinks().getSmallThumbnail())
                .into(customViewHolder.smallThumbnail);
    }

    @Override
    public int getItemCount() {
        return dataSet.getItems().size() > 0 ? dataSet.getItems().size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView bookTitle, authors, numRating, language;
        ImageView smallThumbnail;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        bookTitle = itemView.findViewById(R.id.tv_book_title);
        authors = itemView.findViewById(R.id.tv_authors);
        numRating = itemView.findViewById(R.id.tv_rating);
        language = itemView.findViewById(R.id.tv_language);
        smallThumbnail = itemView.findViewById(R.id.iv_small_book_thumbnail);
    }

    @Override
    public void onClick(View v) {

    }
}
}
