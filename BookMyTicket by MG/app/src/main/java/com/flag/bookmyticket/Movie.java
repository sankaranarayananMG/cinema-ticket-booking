package com.flag.bookmyticket;

public class Movie {
    private String title;
    private String rating;
    private String genre;
    private String runtime;
    private String language;
    private int image;

    public Movie(String title, String rating, String genre, String runtime, String language, int image) {
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.runtime = runtime;
        this.language = language;
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
}