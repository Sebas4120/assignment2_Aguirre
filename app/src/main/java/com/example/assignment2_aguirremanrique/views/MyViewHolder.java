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

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView Title;
    TextView Year;

    ImageView MoviePoster;

    //....xxxx
    ItemClickListener itemClickListener;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        //This is the cell
        Title = itemView.findViewById(R.id.title_txt);
        Year = itemView.findViewById(R.id.year_text);
        MoviePoster = itemView.findViewById(R.id.moviePoster);

        //....xxxx
        this.itemClickListener = itemClickListener;

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("tag", "onViewHolder Click");
//
//                //...xxxxxx
//
//
//                // Get the movie object for the clicked position (itemView is already bound to a position)
//                MoviesModel movie = (MoviesModel) itemView.getTag(); // Tag holds the MoviesModel
//
//                // Trigger the listener when clicked
//                if (movie != null) {
//                    // Pass the IMDb ID to the MovieDetailActivity to fetch more details
//                    Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
//                    intent.putExtra("IMDB_ID", movie.getImdbID());  // Pass IMDb ID to the next activity
//                    itemView.getContext().startActivity(intent);
//                }
//            }
//        });

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MoviesModel movie = (MoviesModel) itemView.getTag();
        if (movie != null) {
            itemClickListener.onMovieClick(movie.getImdbID());
        }
    }
}
