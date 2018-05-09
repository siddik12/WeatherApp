package com.example.rasel.weatherreport.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rasel.weatherreport.R;
import com.example.rasel.weatherreport.adapter.ForecastWeatherAdapter;
import com.example.rasel.weatherreport.api.WeatherApi;
import com.example.rasel.weatherreport.model.Demo;
import com.example.rasel.weatherreport.model.ForecastWeatherResponse;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rasel.weatherreport.utils.Constant.WeatherLocation.latitude;
import static com.example.rasel.weatherreport.utils.Constant.WeatherLocation.longitude;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWeek extends Fragment {

    private RecyclerView recyclerView;
    private WeatherApi weatherApi;
    private String units = "metric";//imperial
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private String city;
    private String count;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        latitude = 23.75;
        longitude = 90.39;
        getForecastWeatherData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_week, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        ArrayList<Demo> demos = new ArrayList<>();

        Demo demo = new Demo("Today","25°C","45°C","9 May,2018",R.drawable.cloud);
        Demo demo1 = new Demo("Today","25°C","45°C","9 May,2018",R.drawable.cloud);
        Demo demo2 = new Demo("Today","25°C","45°C","9 May,2018",R.drawable.cloud);
        Demo demo3 = new Demo("Today","25°C","45°C","9 May,2018",R.drawable.cloud);

        demos.add(demo);
        demos.add(demo1);
        demos.add(demo2);
        demos.add(demo3);
        ForecastWeatherAdapter adapter = new ForecastWeatherAdapter(demos);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        getForecastWeatherData();
        return view;
    }

    private void getForecastWeatherData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        String forecastWeatherUrl = String.format(Locale.US, "forecast?lat=%f&lon=%f&units=%s&cnt=%s&appid=%s", latitude, longitude, units,count, getString(R.string.weather_api_key));

        Call<ForecastWeatherResponse> responseCall = weatherApi.getForecastWeatherData(forecastWeatherUrl);
        responseCall.enqueue(new Callback<ForecastWeatherResponse>() {
            @Override
            public void onResponse(Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                if (response.code() == 200) {
                    ForecastWeatherResponse forecastWeatherResponse = response.body();
                    ArrayList<ForecastWeatherResponse.List> lists = new ArrayList<>();
                    Toast.makeText(getContext(), ""+forecastWeatherResponse.getList().get(0).getTemp().getMin(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), ""+response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForecastWeatherResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
