package com.example.assignment2_aguirremanrique.views;

import android.content.ClipData;
import android.content.Context;
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

    List<MoviesModel> movies;
    Context context;
    //....xxxx
    ItemClickListener itemClickListener;

    public MyAdapter(List<MoviesModel> movies, Context context, ItemClickListener itemClickListener) {
         this.movies = movies;
        this.context = context;
        this.itemClickListener = itemClickListener;
        //this.movies = movies != null ? movies : new ArrayList<>();
    }

    //......xxxxxxxxxxxxxx
//   public void setClickListener (ItemClickListener itemClickListener){
//        this.itemClickListener=itemClickListener;
//    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        //MyViewHolder myViewHolder = new MyViewHolder(itemView);
//        MyViewHolder myViewHolder = new MyViewHolder(itemView, this.itemClickListener);

        //return myViewHolder;
        return new MyViewHolder(itemView, itemClickListener);
    }

    //This method bind the data to the view
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MoviesModel movie = movies.get(position);

        holder.Title.setText(movie.getTitle());
        holder.Year.setText(movie.getYear());

        // Load the movie poster using Glide
        Glide.with(context)
                .load(movie.getPosterUrl())  // URL from the MoviesModel
                .into(holder.MoviePoster);  // Set the image into the ImageView

        //.......xxxx
        // Guardar el objeto en la vista para que MyViewHolder lo pueda recuperar
        holder.itemView.setTag(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // New method to update movie list
    public void updateMovies(List<MoviesModel> newMovies) {
        if (newMovies != null) {
            this.movies = newMovies;
            notifyDataSetChanged();
        } // Refresh the RecyclerView
    }
}
