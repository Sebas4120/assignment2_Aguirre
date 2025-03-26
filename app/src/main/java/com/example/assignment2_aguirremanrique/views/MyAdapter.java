package com.example.assignment2_aguirremanrique.views;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment2_aguirremanrique.R;
import com.example.assignment2_aguirremanrique.model.MoviesModel;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    // List to hold movie data
    List<MoviesModel> movies;
    // Context to access resources
    Context context;
    // Click listener interface
    ItemClickListener itemClickListener;

    // Constructor to initialize the adapter with a list of movies and context
    public MyAdapter(List<MoviesModel> movies, Context context) {
         this.movies = movies;
        this.context = context;
    }

    // Method to set a custom click listener
   public void setClickListener (ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }


    // Creates a new ViewHolder for each item in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for individual list items
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        // Create and return a ViewHolder with the inflated view
        MyViewHolder myViewHolder = new MyViewHolder(itemView, this.itemClickListener);
        return myViewHolder;
    }

    //This method binds data to the views in the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get the current movie from the list
        MoviesModel movie = movies.get(position);

        holder.Title.setText(movie.getTitle());
        holder.Year.setText(movie.getYear());

        // Load the movie poster using Glide
        Glide.with(context)
                .load(movie.getPosterUrl())  // URL from the MoviesModel
                .into(holder.MoviePoster);  // Set the image into the ImageView



        // Handle item click events
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onMovieClick(movie.getImdbID());
            }
        });




    }

    // Returns the total number of movies in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // Method to update the movie list dynamically
    public void updateMovies(List<MoviesModel> newMovies) {
        if (newMovies != null) {
            // Update the list
            this.movies = newMovies;
            // Notify the adapter to refresh the RecyclerView
            notifyDataSetChanged();
        }
    }
}
