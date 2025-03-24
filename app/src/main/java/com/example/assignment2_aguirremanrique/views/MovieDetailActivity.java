package com.example.assignment2_aguirremanrique.views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.assignment2_aguirremanrique.R;
import com.example.assignment2_aguirremanrique.model.MovieDetailModel;
import com.example.assignment2_aguirremanrique.viewModel.MoviesViewModel;

public class MovieDetailActivity extends AppCompatActivity {


    private TextView titleTextView, yearTextView, plotTextView;
    private ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);

        // Initialize the views
        titleTextView = findViewById(R.id.detail_title);
        yearTextView = findViewById(R.id.detail_year);
        plotTextView = findViewById(R.id.detail_plot);
        posterImageView = findViewById(R.id.detail_poster);



        // Get the imdbID from the Intent
        String imdbID = getIntent().getStringExtra("IMDB_ID");

        // Initialize ViewModel
        MoviesViewModel moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        // Observe LiveData for movie details
        moviesViewModel.getMovieDetailData().observe(this, movieDetail -> {
            // Update the UI with the movie details
            if (movieDetail != null) {
                titleTextView.setText(movieDetail.getTitle());
                yearTextView.setText(movieDetail.getYear());
                plotTextView.setText(movieDetail.getPlot());

                // Use Glide to load the poster image
                Glide.with(this)
                        .load(movieDetail.getPosterUrl())
                        .into(posterImageView);
            }
        });

        // Fetch movie details using imdbID
        moviesViewModel.getMovieDetails(imdbID);
    }
}
