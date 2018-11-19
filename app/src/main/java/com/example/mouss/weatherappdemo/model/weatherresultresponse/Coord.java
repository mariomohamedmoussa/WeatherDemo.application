package com.example.mouss.weatherappdemo.model.weatherresultresponse;

public class Coord {
    private double lon ;
    private double lat ;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return new StringBuilder("[").append(this.lat).append(',').append(this.lon).append("]").toString();
    }
}
