package com.example.assignment2_aguirremanrique.model;

public class MoviesModel {

    private String Title, Year, imdbID;

    private String PosterUrl;


    public MoviesModel(){

    }

    // Constructor to initialize fields
    public MoviesModel(String title, String year, String posterUrl, String imdbID) {
        this.Title = title;
        this.Year = year;
        this.PosterUrl = posterUrl;
        this.imdbID=imdbID;
    }

    public MoviesModel(String title, String year, String posterUrl) {
        this.Title = title;
        this.Year = year;
        this.PosterUrl = posterUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    // Getter and setter for PosterUrl
    public String getPosterUrl() {
        return PosterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.PosterUrl = posterUrl;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}
