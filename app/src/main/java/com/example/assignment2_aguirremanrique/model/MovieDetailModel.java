package com.example.assignment2_aguirremanrique.model;

public class MovieDetailModel {

    private String title;
    private String year;
    private String plot;
    private String posterUrl;

    // Constructor
    public MovieDetailModel(String title, String year, String plot, String posterUrl) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.posterUrl = posterUrl;
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
}
