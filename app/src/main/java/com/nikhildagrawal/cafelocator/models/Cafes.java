package com.nikhildagrawal.cafelocator.models;

public class Cafes {

    private String cafeName;
    private Double rating;

    public Cafes(String cafeName, double rating) {
        this.cafeName = cafeName;
        this.rating = rating;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
