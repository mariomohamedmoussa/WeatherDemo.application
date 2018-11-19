package com.example.mouss.weatherappdemo.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mouss.weatherappdemo.R;
import com.example.mouss.weatherappdemo.controller.adapter.WeatherForecastAdapter;
import com.example.mouss.weatherappdemo.model.forecastweatherresult.WeatherForecastResult;
import com.example.mouss.weatherappdemo.retrofit.APIService;
import com.example.mouss.weatherappdemo.retrofit.RetrofitResponse;
import com.example.mouss.weatherappdemo.utils.Collection;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastWeatherFragment extends Fragment {

    static ForecastWeatherFragment forecastWeatherFragmentInstance;
    CompositeDisposable compositeDisposable;
    APIService apiService;

    private LinearLayout mainInfo;
    private TextView tvCityName;
    private TextView tvGeoCooder;
    private RecyclerView recycleForecasting;


    public static ForecastWeatherFragment getInstance() {
        if (forecastWeatherFragmentInstance == null)
            forecastWeatherFragmentInstance = new ForecastWeatherFragment();
        return forecastWeatherFragmentInstance;
    }

    public ForecastWeatherFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitResponse.getInstance();
        apiService = retrofit.create(APIService.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_forecast_weather, container, false);
        initComonentFragmentGui(view);
        getForecastWeatherInformation();
        return view;
    }

    private void getForecastWeatherInformation() {
        compositeDisposable.add(apiService.getForecastWeather(
                String.valueOf(Collection.lat),
                String.valueOf(Collection.lon),
                Collection.API_ID,
                "metric"
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecastResult>() {
                    @Override
                    public void accept(WeatherForecastResult weatherForecastResult) throws Exception {
                        // display forecasting weather
                        displayForeCastingWeather(weatherForecastResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error",""+throwable.getMessage());
                    }
                })
        );
    }

    private void displayForeCastingWeather(WeatherForecastResult weatherForecastResult) {
        tvCityName.setText(new StringBuilder(weatherForecastResult.getCity().getName()));
        tvGeoCooder.setText(new StringBuilder(weatherForecastResult.getCity().getCoord().toString()));

        WeatherForecastAdapter weatherForecastAdapter  = new WeatherForecastAdapter(getContext(),weatherForecastResult);
        recycleForecasting.setAdapter(weatherForecastAdapter);
    }

    public void initComonentFragmentGui(View view) {
        mainInfo = (LinearLayout) view.findViewById(R.id.mainInfo);
        tvCityName = (TextView) view.findViewById(R.id.tvCityName);
        tvGeoCooder = (TextView) view.findViewById(R.id.tvGeoCooder);
        recycleForecasting = (RecyclerView) view.findViewById(R.id.recycleForecasting);
        recycleForecasting.setHasFixedSize(true);
        recycleForecasting.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
