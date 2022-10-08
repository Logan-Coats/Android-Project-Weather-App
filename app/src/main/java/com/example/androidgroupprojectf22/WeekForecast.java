package com.example.androidgroupprojectf22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WeekForecast extends AppCompatActivity { //weeklyforecast is the 'Daily' activity, as it will show the weather for the next few days.
    private String appid = "0ffb075f7e03483db28200427220310";
    private String location = "64468";
    private String url = "https://api.weatherapi.com/v1/current.json?key="+appid+"&q="+location;
    private String jsonResponse = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_forecast);

        Intent intent = getIntent();
        if(intent.getStringExtra("location")==""){
            //get current location here and store in private string
        }
    }

    public void toSearch(View v){
        Intent search = new Intent(this, MainActivity.class);
        startActivity(search);
    }

}