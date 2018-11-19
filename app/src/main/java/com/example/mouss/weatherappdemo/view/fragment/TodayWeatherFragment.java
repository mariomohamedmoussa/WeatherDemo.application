package com.example.mouss.weatherappdemo.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mouss.weatherappdemo.R;
import com.example.mouss.weatherappdemo.model.weatherresultresponse.WeatherResult;
import com.example.mouss.weatherappdemo.retrofit.APIService;
import com.example.mouss.weatherappdemo.retrofit.RetrofitResponse;
import com.example.mouss.weatherappdemo.utils.Collection;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TodayWeatherFragment extends Fragment {


    private LinearLayout weatherPanel;
    private TextView tvCityName;
    private ImageView ivWeather;
    private TextView tvTxtTemperature;
    private TextView tvDescription;
    private TextView tvDateTime;
    private TextView tvWind;
    private TextView tvPressure;
    private TextView tvHummidity;
    private TextView tvSunRise;
    private TextView tvSunSet;
    private TextView tvGeoCoorder;
    private ProgressBar loadingPar;
    CompositeDisposable compositeDisposable;
    APIService apiService;
    double lat;
    double lon;


    static TodayWeatherFragment instance;


    public static TodayWeatherFragment getInstance() {
        if (instance == null) {
            instance = new TodayWeatherFragment();
        }
        return instance;
    }


    public TodayWeatherFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitResponse.getInstance();
        apiService = retrofit.create(APIService.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_weather, container, false);
        initComponentFragmentGui(view);
        getWeatherInformation();
        return view;
    }

    private void initComponentFragmentGui(View view) {
        weatherPanel = (LinearLayout) view.findViewById(R.id.weatherPanel);
        tvCityName = (TextView) view.findViewById(R.id.tvCityName);
        ivWeather = (ImageView) view.findViewById(R.id.ivWeather);
        tvTxtTemperature = (TextView) view.findViewById(R.id.tvTxtTemperature);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvDateTime = (TextView) view.findViewById(R.id.tvDateTime);
        tvWind = (TextView) view.findViewById(R.id.tvWind);
        tvPressure = (TextView) view.findViewById(R.id.tvPressure);
        tvHummidity = (TextView) view.findViewById(R.id.tvHummidity);
        tvSunRise = (TextView) view.findViewById(R.id.tvSunRise);
        tvSunSet = (TextView) view.findViewById(R.id.tvSunSet);
        tvGeoCoorder = (TextView) view.findViewById(R.id.tvGeoCoorder);
        loadingPar = (ProgressBar) view.findViewById(R.id.loadingPar);
    }

    private void getWeatherInformation() {
        if (Collection.Loc.contains("myLoc")){
             lat = Collection.currentLocation.getLatitude();
             lon = Collection.currentLocation.getLongitude();
        }else {
            lat = Collection.lat;
            lon = Collection.lon;
        }
            compositeDisposable.add(apiService.getWeather(
                    String.valueOf(lat),
                    String.valueOf(lon),
                    Collection.API_ID,
                    "metric")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<WeatherResult>() {
                        @Override
                        public void accept(WeatherResult weatherResult) throws Exception {
                            // load image
                            Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(weatherResult.getWeather()
                                    .get(0).getIcon()).append(".png").toString()).into(ivWeather);

                            // Load information
                            tvCityName.setText(weatherResult.getName());
                            tvDescription.setText(new StringBuilder("weather in ").append(weatherResult.getName()).toString());
                            tvTxtTemperature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp()))
                                    .append("°C").toString());
                            tvDateTime.setText(Collection.getDate(weatherResult.getDt()));
                            tvPressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append(" hpa")
                                    .toString());
                            tvHummidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).
                                    append(" %").toString());
                            tvSunRise.setText(Collection.getTime(weatherResult.getSys().getSunrise()));
                            tvSunSet.setText(Collection.getTime(weatherResult.getSys().getSunset()));
                            tvGeoCoorder.setText(new StringBuilder(weatherResult.getCoord().
                                    toString()));

                            // Display panel
                            weatherPanel.setVisibility(View.VISIBLE);
                            loadingPar.setVisibility(View.GONE);

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(getActivity(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
            );
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
