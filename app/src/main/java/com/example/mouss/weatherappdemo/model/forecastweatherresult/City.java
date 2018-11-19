package com.example.mouss.weatherappdemo.model.forecastweatherresult;

import com.example.mouss.weatherappdemo.model.weatherresultresponse.Coord;

public class City {

    private int id ;
    private String name ;
    private Coord coord ;
    private String country ;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
