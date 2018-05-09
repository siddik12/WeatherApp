package com.example.rasel.weatherreport.api;

import com.example.rasel.weatherreport.model.CurrentWeatherResponse;
import com.example.rasel.weatherreport.model.ForecastWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherApi {

    @GET
    Call<CurrentWeatherResponse> getCurrentWeatherData(@Url String url);
    @GET
    Call<ForecastWeatherResponse> getForecastWeatherData(@Url String url);

}
