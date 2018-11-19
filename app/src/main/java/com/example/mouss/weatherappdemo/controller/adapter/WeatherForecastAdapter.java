package com.example.mouss.weatherappdemo.controller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mouss.weatherappdemo.R;
import com.example.mouss.weatherappdemo.model.forecastweatherresult.WeatherForecastResult;
import com.example.mouss.weatherappdemo.utils.Collection;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {
    Context context;
    WeatherForecastResult weatherForecastResult;

    public WeatherForecastAdapter(Context context, WeatherForecastResult weatherForecastResult) {
        this.context = context;
        this.weatherForecastResult = weatherForecastResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_weather_forecasting,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // load image
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherForecastResult.getList().get(position).getWeather().get(0).getIcon()).
                        append(".png").toString()).into(holder.ivWeather);

        holder.tvdate.setText(new StringBuilder(Collection.getDate(weatherForecastResult.getList().get(position).getDt())));
        holder.tvDescription.setText(new StringBuilder(weatherForecastResult.getList()
                .get(position).getWeather().get(0).getDescription()));
        holder.tvTxtTemperature.setText(new StringBuilder(String.valueOf(weatherForecastResult.getList().get(position)
                .getMain().getTemp())).append(" °C"));

    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.getList().size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvdate;
        private ImageView ivWeather;
        private TextView tvTxtTemperature;
        private TextView tvDescription;



        public MyViewHolder(View itemView) {
            super(itemView);
            tvdate = (TextView) itemView.findViewById(R.id.tvdate);
            ivWeather = (ImageView) itemView.findViewById(R.id.ivWeather);
            tvTxtTemperature = (TextView) itemView.findViewById(R.id.tvTxtTemperature);
            tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
        }
    }
}
