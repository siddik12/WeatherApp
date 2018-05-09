package com.example.rasel.weatherreport.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasel.weatherreport.R;
import com.example.rasel.weatherreport.adapter.ForecastWeatherAdapter;
import com.example.rasel.weatherreport.model.Demo;
import com.example.rasel.weatherreport.model.ForecastWeatherResponse;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWeek extends Fragment {

    private RecyclerView recyclerView;
    public FragmentWeek() {
        // Required empty public constructor
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

        return view;
    }

}
