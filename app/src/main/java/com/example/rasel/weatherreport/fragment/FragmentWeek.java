package com.example.rasel.weatherreport.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasel.weatherreport.R;
import com.example.rasel.weatherreport.model.Demo;
import com.example.rasel.weatherreport.model.ForecastWeatherResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWeek extends Fragment {


    public FragmentWeek() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_week, container, false);
        view.findViewById(R.id.recyclerView);





        return view;
    }

}
