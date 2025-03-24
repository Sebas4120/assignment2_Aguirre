package com.example.assignment2_aguirremanrique.model;

public class MovieDetailModel {

    private String title, year, plot, posterUrl, genre, runtime, imdbRating;




    // Constructor
    public MovieDetailModel(String title, String year, String plot, String posterUrl, String genre, String runtime, String imdbRating) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.posterUrl = posterUrl;
        this.genre = genre;
        this.runtime=runtime;
        this.imdbRating=imdbRating;

    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }
}
