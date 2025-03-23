package com.example.assignment2_aguirremanrique.views;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
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

    MoviesViewModel viewModel;

    ActivityMainBinding binding;

    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instantiate the binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        //First you have to instantiate your viewModel
        viewModel =new ViewModelProvider(this).get(MoviesViewModel.class);

        myAdapter = new MyAdapter(new ArrayList<>(),this);
        binding.recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMovies.setAdapter(myAdapter);

        //..........xxxx
        myAdapter.setClickListener(this);


        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = binding.searchText.getText().toString().trim();
                if (!query.isEmpty()) {
                    // Call the ViewModel to search movies
                    viewModel.searchMovies(query);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Observe LiveData for movies data
        viewModel.getMoviesData().observe(this, movies -> {
            if (movies != null) {
                // Update the adapter with the new movie data
                myAdapter.updateMovies(movies);
            }
        });


    }

    //Click listener for every cell: this method is the one that we want to call from our viewHolder
    @Override
    public void onMovieClick(String imdbID) {
        // Handle the click event and navigate to movie details
        Intent intent = new Intent(this, MovieDetailActivity.class);  // Fix: Change to MovieDetailActivity
        intent.putExtra("IMDB_ID", imdbID);  // Pass imdbID to the next activity
        startActivity(intent);
    }


}