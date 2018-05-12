package com.example.rasel.weatherreport.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rasel.weatherreport.MainActivity;
import com.example.rasel.weatherreport.R;
import com.example.rasel.weatherreport.api.WeatherApi;
import com.example.rasel.weatherreport.model.CurrentWeatherResponse;
import com.example.rasel.weatherreport.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rasel.weatherreport.MainActivity.latitude;
import static com.example.rasel.weatherreport.MainActivity.longitude;

public class FragmentCurrent extends Fragment {
    private WeatherApi weatherApi;
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private String city;
    private TextView textViewTemp, textViewDate, textViewDay, textViewCity,textViewWeatherMain,textViewMinValue,textViewMaxValue,textViewHumidity,textViewPressure,textViewSunrise,textViewSunset;
    private ImageView imageViewIcon;
    private double temp;

    public FragmentCurrent (){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("DATA", String.valueOf(latitude));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_current, container, false);
        getCurrentWeatherData();

        Log.d("DATA", String.valueOf(latitude));

        textViewTemp = view.findViewById(R.id.textViewTemp);
        textViewDate = view.findViewById(R.id.textViewDate);
        textViewDay = view.findViewById(R.id.textViewDay);
        textViewCity = view.findViewById(R.id.textViewCity);
        textViewWeatherMain = view.findViewById(R.id.textViewWeatherMain);
        textViewMinValue = view.findViewById(R.id.textViewMinValue);
        textViewMaxValue = view.findViewById(R.id.textViewMaxValue);
        textViewHumidity = view.findViewById(R.id.textViewHumidity);
        imageViewIcon = view.findViewById(R.id.imageViewIcon);
        textViewPressure = view.findViewById(R.id.textViewPressure);
        textViewSunrise = view.findViewById(R.id.textViewSunrise);
        textViewSunset = view.findViewById(R.id.textViewSunset);



        return view;
    }

    private void getCurrentWeatherData() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        weatherApi = retrofit.create(WeatherApi.class);



        String units = "metric";
        String currentWeatherUrl = String.format(Locale.US, "weather?lat=%f&lon=%f&units=%s&appid=%s", latitude, longitude, units, getString(R.string.weather_api_key));

        //String cityWeatherUrl = String.format(Locale.US,"weather?q=%s",city );

        Call<CurrentWeatherResponse> responseCall = weatherApi.getCurrentWeatherData(currentWeatherUrl);

        responseCall.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if (response.code() == 200) {
                    CurrentWeatherResponse currentWeatherResponse =
                            response.body();
                    //textViewTemp.setText(String.format("%s ℉", currentWeatherResponse.getMain().getTemp()));
                    textViewTemp.setText(String.format("%s℃", currentWeatherResponse.getMain().getTemp()));
                    textViewCity.setText(currentWeatherResponse.getName());
                    textViewSunrise.setText(Constant.DateTimeFormat.getTimeFromUnix(currentWeatherResponse.getSys().getSunrise()));
                    textViewSunset.setText(Constant.DateTimeFormat.getTimeFromUnix(currentWeatherResponse.getSys().getSunset()));
                    textViewDate.setText(Constant.DateTimeFormat.unixToDate(currentWeatherResponse.getDt()));
                    textViewDay.setText(Constant.DateTimeFormat.unixToDay(currentWeatherResponse.getDt()));
                    textViewMinValue.setText(String.format("%s℃", currentWeatherResponse.getMain().getTempMin()));
                    textViewMaxValue.setText(String.format("%s℃", currentWeatherResponse.getMain().getTempMax()));
                    textViewWeatherMain.setText(currentWeatherResponse.getWeather().get(0).getMain());
                    textViewHumidity.setText(String.format(Locale.US,"%d%%", currentWeatherResponse.getMain().getHumidity()));
                    textViewPressure.setText(String.format("%s hPa", currentWeatherResponse.getMain().getPressure()));

                    Uri uri = Uri.parse("http://openweathermap.org/img/w/" + currentWeatherResponse.getWeather().get(0).getIcon() + ".png");
                    Picasso.get().load(uri).into(imageViewIcon);

                    Log.d("DATA",response.body().getName());

                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Log.d("DATA",t.getMessage());
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }





}