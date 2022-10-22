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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hourly_forecast);
        hourlyAdapter adapter = new hourlyAdapter();
        RecyclerView hourlyWeather = findViewById(R.id.hourlyWeather);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        hourlyWeather.setLayoutManager(myManager);
        hourlyWeather.setAdapter(adapter);
    }

    //TODO: implement api call and populate fields and recycler view with the next 24 hours of weather (temp, condition)

    public void onSearchBTNClick(View v){;
        Intent search = new Intent(this, MainActivity.class);
        startActivity(search);
    }


    private void setAdapter() {
        hourlyAdapter adapter = new hourlyAdapter();
        RecyclerView hourlyWeather = findViewById(R.id.hourlyWeather);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        hourlyWeather.setLayoutManager(myManager);
        hourlyWeather.setAdapter(adapter);

    }
}
