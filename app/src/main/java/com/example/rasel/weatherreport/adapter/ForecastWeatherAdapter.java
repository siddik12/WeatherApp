package com.example.rasel.weatherreport.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rasel.weatherreport.R;
import com.example.rasel.weatherreport.model.Demo;
import com.example.rasel.weatherreport.model.ForecastWeatherResponse;

import java.util.List;

public class ForecastWeatherAdapter extends RecyclerView.Adapter<ForecastWeatherAdapter.ForecastViewHolder> {

    private List<Demo> forecastList;
    private List<ForecastWeatherResponse> forecastWeatherResponses;

    public ForecastWeatherAdapter(List<Demo> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_view,parent,false);

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        Demo demo = forecastList.get(position);
        holder.textViewToday.setText(demo.getToday());
        holder.textViewMaxValue.setText(demo.getMax());
        holder.textViewMinValue.setText(demo.getMin());
        holder.imageViewIcon.setImageResource(demo.getIcon());
        holder.textViewDate.setText(demo.getDate());
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder{
        TextView textViewToday,textViewMinValue,textViewDate,textViewMaxValue;
        ImageView imageViewIcon;
        ForecastViewHolder(View itemView) {
            super(itemView);

            textViewToday = itemView.findViewById(R.id.textViewToday);
            textViewMinValue = itemView.findViewById(R.id.textViewMinValue);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewMaxValue = itemView.findViewById(R.id.textViewMaxValue);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
