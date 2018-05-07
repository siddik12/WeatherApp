package com.example.rasel.weatherreport.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rasel.weatherreport.fragment.FragmentCurrent;
import com.example.rasel.weatherreport.fragment.FragmentWeek;

public class CurrentWeatherAdapter extends FragmentStatePagerAdapter {
    public CurrentWeatherAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if (position==0){
            fragment = new FragmentCurrent();
        }else if (position==1){
            fragment = new FragmentWeek();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Current";
        }else if (position == 1){
            return "7 Days Forecast";
        }
        return null;
    }
}
