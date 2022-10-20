package com.example.androidgroupprojectf22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//Hourly Forecast - page where all things in the hourly_forecast.xml file will be programmed
//Responsible for taking input from the Daily Forecast and representing hourly updates on the weather, temp, etc.

public class hourly_Forecast extends AppCompatActivity {
    private ArrayList<hourlyWeatherEntry> weatherEntries;
    private RecyclerView hourlyWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hourly_forecast);
        weatherEntries = new ArrayList<>();
        hourlyWeather = findViewById(R.id.hourlyWeather);
    }

    //TODO: implement api call and populate fields and recycler view with the next 24 hours of weather (temp, condition)

    public void onSearchBTNClick(View v){
        Intent search = new Intent(this, MainActivity.class);
        startActivity(search);
    }

    private void testInfo() {
        weatherEntries.add(new hourlyWeatherEntry("70"));
        weatherEntries.add(new hourlyWeatherEntry("80"));
        weatherEntries.add(new hourlyWeatherEntry("90"));

    }

    private void setAdapter() {
        hourlyWeatherAdapter adapter = new hourlyWeatherAdapter(weatherEntries);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getApplicationContext());
        hourlyWeather.setLayoutManager(layoutmanager);
        hourlyWeather.setItemAnimator(new DefaultItemAnimator());
        hourlyWeather.setAdapter(adapter);

    }
}
