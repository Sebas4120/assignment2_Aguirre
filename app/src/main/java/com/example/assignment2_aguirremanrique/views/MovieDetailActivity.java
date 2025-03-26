package com.example.assignment2_aguirremanrique.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.assignment2_aguirremanrique.R;
import com.example.assignment2_aguirremanrique.databinding.ActivityMainBinding;
import com.example.assignment2_aguirremanrique.databinding.ActivityMovieDetailBinding;
import com.example.assignment2_aguirremanrique.model.MovieDetailModel;
import com.example.assignment2_aguirremanrique.viewModel.MoviesViewModel;

public class MovieDetailActivity extends AppCompatActivity {

    ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the imdbID from the Intent
        String imdbID = getIntent().getStringExtra("IMDB_ID");

        // Initialize ViewModel
        MoviesViewModel moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        // Observe LiveData for movie details
        moviesViewModel.getMovieDetailData().observe(this, movieDetail -> {
            // Update the UI with the movie details
            if (movieDetail != null) {
                binding.detailTitle.setText(movieDetail.getTitle());
                binding.detailYear.setText(movieDetail.getYear());
                binding.detailPlot.setText(movieDetail.getPlot());
                binding.detailGenre.setText(movieDetail.getGenre());
                binding.detailTime.setText(movieDetail.getRuntime());
                binding.detailScore.setText(movieDetail.getImdbRating());

                // Use Glide to load the poster image
                Glide.with(this)
                        .load(movieDetail.getPosterUrl())
                        //.into(posterImageView);
                        .into(binding.detailPoster);
            }
        });

        // Fetch movie details using imdbID
        moviesViewModel.getMovieDetails(imdbID);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Closes the activity and returns to the previous screen
                finish();
            }
        });

    }
}
