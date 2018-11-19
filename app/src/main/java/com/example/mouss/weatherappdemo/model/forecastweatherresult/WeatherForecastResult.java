package com.example.mouss.weatherappdemo.model.forecastweatherresult;


import java.util.List;

public class WeatherForecastResult {

    private String cod ;
    private double message ;
    private int cnt ;
    private List<Mylist> list ;
    private City city ;

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<Mylist> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }
}
