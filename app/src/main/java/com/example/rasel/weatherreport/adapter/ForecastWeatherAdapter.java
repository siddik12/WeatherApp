package com.example.rasel.weatherreport.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasel.weatherreport.model.Demo;
import com.example.rasel.weatherreport.model.ForecastWeatherResponse;

import java.util.List;

public class ForecastWeatherAdapter extends RecyclerView.Adapter<ForecastWeatherAdapter.ForecastViewHolder> {

    List<Demo> forecastList;

    public ForecastWeatherAdapter(List<Demo> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder{
        public ForecastViewHolder(View itemView) {
            super(itemView);
        }
    }
}
