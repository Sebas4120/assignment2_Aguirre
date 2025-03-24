package com.example.assignment2_aguirremanrique.viewModel;

import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment2_aguirremanrique.model.MovieDetailModel;
import com.example.assignment2_aguirremanrique.model.MoviesModel;
import com.example.assignment2_aguirremanrique.utils.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MoviesViewModel extends ViewModel {

    //Live data
    private final MutableLiveData <List<MoviesModel>> moviesData  = new MutableLiveData<>();

    // LiveData for movie details...xxxx
    private final MutableLiveData<MovieDetailModel> movieDetailData = new MutableLiveData<>();


    // LiveData for error messages (optional)
    private final MutableLiveData<String> errorData = new MutableLiveData<>();

    //Reference to our model
    MoviesModel moviesModel = new MoviesModel();

    // Optional: Expose error messages as LiveData
    public LiveData<String> getErrorData() {
        return errorData;
    }

    //Return my live data object
    // Get the list of movies

    public LiveData<List<MoviesModel>> getMoviesData(){
        return moviesData;
    }

    // LiveData for movie detail.....xxxxxxxxxxx
    public LiveData<MovieDetailModel> getMovieDetailData() {
        return movieDetailData;
    }



    //This method is actually the one who get the data
    public void searchMovies(String query){
        String url = "https://www.omdbapi.com/?apikey=4c740af3&s=" + query + "&type=movie";

        ApiClient.get(url, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Log error or update UI with a message
                errorData.postValue("Error en la solicitud: " + e.getMessage());
                Log.e("API_ERROR", "Error en la solicitud: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (!response.isSuccessful()) {
                    // Return early if the response is not successful
                    errorData.postValue("Error: " + response.code());
                    return;
                }

                String responseData = response.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    if (!jsonObject.has("Search")) {
                        errorData.postValue("No se encontraron películas.");
                        return;
                    }

                    JSONArray searchArray = jsonObject.getJSONArray("Search");
                    List<MoviesModel> moviesList = new ArrayList<>();

                    for (int i = 0; i < searchArray.length(); i++) {
                        JSONObject movieObject = searchArray.getJSONObject(i);
                        String title = movieObject.getString("Title");
                        String year = movieObject.getString("Year");
                        String posterUrl = movieObject.getString("Poster");
                        String imdbID = movieObject.getString("imdbID");

                        moviesList.add(new MoviesModel(title, year, posterUrl, imdbID));
                    }

                    // Actualizar LiveData en el hilo principal
                    moviesData.postValue(moviesList);

                } catch (JSONException e) {
                    // Log error or notify the user of a parsing error
                    errorData.postValue("Error:" + response.message());

                }
            }


        });
    }



    // Fetch movie details (e.g., plot) using imdbID
    public void getMovieDetails(String imdbID) {
        String url = "https://www.omdbapi.com/?apikey=4c740af3&i=" + imdbID;

        ApiClient.get(url, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                errorData.postValue("Error en la solicitud: " + e.getMessage());
                Log.e("API_ERROR", "Error en la solicitud: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    errorData.postValue("Error: " + response.code());
                    return;
                }

                assert response.body() != null;
                String responseData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String title = jsonObject.getString("Title");
                    String year = jsonObject.getString("Year");
                    String plot = jsonObject.getString("Plot");
                    String posterUrl = jsonObject.getString("Poster");

                    // Create MovieDetailModel and update LiveData
                    MovieDetailModel movieDetail = new MovieDetailModel(title, year, plot, posterUrl);
                    movieDetailData.postValue(movieDetail);

                } catch (JSONException e) {
                    errorData.postValue("Error en el parsing.");
                }
            }
        });
    }
}
