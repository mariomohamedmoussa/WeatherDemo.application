package com.example.mouss.weatherappdemo.utils;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Collection {

    public static final String API_ID="e7e21f49f0d7a9b62fa6bb76da2b482c";
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static Location currentLocation = null;
    public static double lat = 41.346176;
    public static double lon = 2.168365;
    public static String Loc = null;

    public static String getDate(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd EEE MM yyyy");
        String formated = simpleDateFormat.format(date);
        return formated;
    }

    public static String getTime(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String formated = simpleDateFormat.format(date);
        return formated;
    }
}
