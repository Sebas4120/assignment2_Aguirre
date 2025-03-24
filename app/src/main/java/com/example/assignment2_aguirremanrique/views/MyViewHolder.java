package com.example.assignment2_aguirremanrique.views;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2_aguirremanrique.R;
import com.example.assignment2_aguirremanrique.model.MoviesModel;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView Title;
    TextView Year;

    ImageView MoviePoster;

    //....xxxx
    ItemClickListener itemClickListener;

    public MyViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);

        //This is the cell
        Title = itemView.findViewById(R.id.title_txt);
        Year = itemView.findViewById(R.id.year_text);
        MoviePoster = itemView.findViewById(R.id.moviePoster);

        //....xxxx
        this.itemClickListener = itemClickListener;

        itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    MoviesModel movie = (MoviesModel) v.getTag(); // Retrieve movie object
                    if (movie != null) {
                        itemClickListener.onMovieClick(movie.getImdbID()); // Pass IMDb ID as a string
                    } else {
                        Log.e("ERROR", "Movie object is null");
                    }
                }
            }
        });


    }


}
