package com.example.mouss.weatherappdemo.retrofit;

import com.example.mouss.weatherappdemo.model.forecastweatherresult.WeatherForecastResult;
import com.example.mouss.weatherappdemo.model.weatherresultresponse.WeatherResult;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService  {
    @GET("weather")
    io.reactivex.Observable<WeatherResult> getWeather(@Query("lat")String lat,
                                                      @Query("lon")String lng,
                                                      @Query("appid")String appId,
                                                      @Query("units")String unit);



    @GET("forecast")
    io.reactivex.Observable<WeatherForecastResult> getForecastWeather(@Query("lat")String lat,
                                                                      @Query("lon")String lng,
                                                                      @Query("appid")String appId,
                                                                      @Query("units")String unit);
}
