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

    // Click listener interface for handling item clicks
    ItemClickListener itemClickListener;

    // Constructor to bind views and handle click events
    public MyViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);

        // Bind the views from the item layout
        Title = itemView.findViewById(R.id.title_txt);
        Year = itemView.findViewById(R.id.year_text);
        MoviePoster = itemView.findViewById(R.id.moviePoster);

        // Initialize the click listener
        this.itemClickListener = itemClickListener;

        // Set an onClickListener for the itemView (each item in the RecyclerView)
        itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                // Get the position of the item clicked in the RecyclerView
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Retrieve the movie object associated with this view
                    MoviesModel movie = (MoviesModel) v.getTag();
                    // If the movie object is not null, trigger the click event
                    if (movie != null) {
                        // Pass IMDb ID to the listener
                        itemClickListener.onMovieClick(movie.getImdbID());
                    } else {
                        // Log error if movie object is null
                        Log.e("ERROR", "Movie object is null");
                    }
                }
            }
        });


    }


}
