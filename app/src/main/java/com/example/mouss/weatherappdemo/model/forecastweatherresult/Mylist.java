package com.example.mouss.weatherappdemo.model.forecastweatherresult;

import com.example.mouss.weatherappdemo.model.weatherresultresponse.Clouds;
import com.example.mouss.weatherappdemo.model.weatherresultresponse.Main;
import com.example.mouss.weatherappdemo.model.weatherresultresponse.Sys;
import com.example.mouss.weatherappdemo.model.weatherresultresponse.Weather;
import com.example.mouss.weatherappdemo.model.weatherresultresponse.Wind;

import java.util.List;

public class Mylist {

    private int dt ;
    private Main main ;

    public int getDt() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Sys getSys() {
        return sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    private List<Weather> weather ;
    private Clouds clouds ;
    private Wind wind ;
    private Rain rain ;
    private Sys sys ;
    private String dt_txt ;
}
