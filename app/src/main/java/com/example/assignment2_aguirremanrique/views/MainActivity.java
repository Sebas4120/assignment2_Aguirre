package com.example.assignment2_aguirremanrique.views;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment2_aguirremanrique.R;
import com.example.assignment2_aguirremanrique.databinding.ActivityMainBinding;
import com.example.assignment2_aguirremanrique.model.MovieDetailModel;
import com.example.assignment2_aguirremanrique.model.MoviesModel;
import com.example.assignment2_aguirremanrique.viewModel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    // ViewModel instance to interact with data
    MoviesViewModel viewModel;

    // View binding for accessing UI elements
    ActivityMainBinding binding;

    // Adapter for RecyclerView
    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instantiate the View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        // Initialize ViewModel
        viewModel =new ViewModelProvider(this).get(MoviesViewModel.class);

        // Initialize adapter with an empty list and set click listener
        myAdapter = new MyAdapter(new ArrayList<>(),this);

        // Set up RecyclerView with LinearLayoutManager and adapter
        binding.recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMovies.setAdapter(myAdapter);

        // Set up click listener for the search button
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting search query from EditText
                String query = binding.searchText.getText().toString().trim();
                if (!query.isEmpty()) {
                    // Call ViewModel to fetch movie data based on query
                    viewModel.searchMovies(query);
                } else {
                    // Show a message if the search query is empty
                    Toast.makeText(MainActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Observe LiveData from ViewModel to update UI when movies data changes
        viewModel.getMoviesData().observe(this, movies -> {
            if (movies != null) {
                // Update the adapter with the new movie data
                myAdapter.updateMovies(movies);
            }
        });


        // Set click listener for RecyclerView items
        myAdapter.setClickListener(this);
    }

    //Handles click events on a movie item.
    //Navigates to the MovieDetailActivity when a movie is clicked.
    @Override
    public void onMovieClick(String imdbID) {
        // Create an Intent to navigate to MovieDetailActivity
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);

        // Pass the selected movie's IMDB ID to the next activity
        intent.putExtra("IMDB_ID", imdbID);

        // Start the MovieDetailActivity
        startActivity(intent);
    }




}