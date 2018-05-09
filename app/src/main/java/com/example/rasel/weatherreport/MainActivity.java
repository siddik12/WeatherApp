package com.example.rasel.weatherreport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rasel.weatherreport.adapter.CurrentWeatherAdapter;
import com.example.rasel.weatherreport.api.WeatherApi;
import com.example.rasel.weatherreport.model.CurrentWeatherResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rasel.weatherreport.utils.Constant.WeatherLocation.latitude;
import static com.example.rasel.weatherreport.utils.Constant.WeatherLocation.longitude;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    private ViewPager viewPager;
    private WeatherApi weatherApi;
    private String units = "metric";//imperial
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = LocationServices.getFusedLocationProviderClient(this);


        viewPager = findViewById(R.id.viewPagerId);
        FragmentManager fragmentManager = getSupportFragmentManager();

        CurrentWeatherAdapter adapter = new CurrentWeatherAdapter(fragmentManager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        checkLocationPermission();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            startActivity(new Intent(MainActivity.this,SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getLocation();
        }
    }

    private void getLocation(){
        checkLocationPermission();
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location == null){
                    return;
                }
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                getCurrentWeatherData();
            }
        });
    }

    private void getCurrentWeatherData() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        String currentWeatherUrl = String.format(Locale.US,"weather?lat=%f&lon=%f&units=%s&appid=%s",latitude,longitude,units,getString(R.string.weather_api_key) );

        //String cityWeatherUrl = String.format(Locale.US,"weather?q=%s",city );

        Call<CurrentWeatherResponse> responseCall = weatherApi.getCurrentWeatherData(currentWeatherUrl);

        responseCall.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if(response.code() == 200){
                    CurrentWeatherResponse currentWeatherResponse =
                            response.body();
                    Log.d("Data", String.valueOf(currentWeatherResponse.getMain().getTemp()));

                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {

            }
        });
    }

    private void checkLocationPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }
}
