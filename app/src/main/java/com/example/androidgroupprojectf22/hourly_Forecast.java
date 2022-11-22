package com.example.androidgroupprojectf22;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
//Hourly Forecast - page where all things in the hourly_forecast.xml file will be programmed
//Responsible for taking input from the Daily Forecast and representing hourly updates on the weather, temp, etc.

public class hourly_Forecast extends AppCompatActivity {

    private String appid = "0ffb075f7e03483db28200427220310";
    private String location = "64468";
    private String url = "https://api.weatherapi.com/v1/forecast.json?key="+appid+"&q=";
    private String jsonResponse = "";
    private WeatherApiObj forecast = new WeatherApiObj();
    private Weather forecastHelper = new Weather();
    public ImageView hourlyWeatherIMG;
    public hourlyAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        jsonResponse = intent.getStringExtra("Weather");
        Log.e("tag", "jsonResponse: " + jsonResponse);

        setContentView(R.layout.hourly_forecast);
        adapter = new hourlyAdapter();
        RecyclerView hourlyWeather = findViewById(R.id.hourlyWeather);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        hourlyWeather.setLayoutManager(myManager);
        hourlyWeather.setAdapter(adapter);

        Weather forecastHelper = new Weather();
        forecast = forecastHelper.convertToObject(jsonResponse);

        setHud();
        setHourlyWeather();
    }


    public void onSearchBTNClick(View v){;
        Intent search = new Intent(this, MainActivity.class);
        startActivity(search);
    }

    private void loadImage(String url) {
        Glide.with(this).load(url).into(hourlyWeatherIMG);
    }


    private void setAdapter() {
        adapter = new hourlyAdapter();
        RecyclerView hourlyWeather = findViewById(R.id.hourlyWeather);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        hourlyWeather.setLayoutManager(myManager);
        hourlyWeather.setAdapter(adapter);
    }

    private void setHud() {
        try {
            Log.e("tag", "loc: " + forecast.location.name + ", "+ forecast.location.region+", "+forecast.location.country);
            TextView currLocTV = findViewById(R.id.currLoc);
            currLocTV.setText(forecast.location.name + ", "+ forecast.location.region+", "+forecast.location.country);

            Log.e("tag", "temp: " + forecast.current.temp_f);
            TextView currTempTV = findViewById(R.id.currTemp);
            currTempTV.setText(Double.toString(forecast.current.temp_f));

            Log.e("tag", "condition:" + forecast.current.condition.text);
            TextView currWeatherTV = findViewById(R.id.currWeather);
            currWeatherTV.setText(forecast.current.condition.text);

            hourlyWeatherIMG = findViewById(R.id.currWeatherIMG);
            loadImage("https://" + forecast.current.condition.icon);

        }
        catch (Error e) {
            Toast.makeText(this, "An error has occurred. Please try again.", Toast.LENGTH_LONG).show();
        }
    }

    private void setHourlyWeather() {
        ArrayList<hourlyModel.hourlyData> temp = new ArrayList<>();
        int currentTime = Calendar.getInstance().get(Calendar.HOUR);
        try {
            for (int i = 0; i < 24; i++) {
                temp.add(new hourlyModel.hourlyData(forecast.forecast.forecastday.get(0).hour.get(i).time,
                        forecast.forecast.forecastday.get(0).hour.get(i).temp_f, forecast.forecast.
                        forecastday.get(0).hour.get(i).condition.icon));
                hourlyModel.getSingleton().addHourlyData(temp.get(i).getTime(), temp.get(i).getTemp(), temp.get(i).getCondition());
                adapter.notifyItemChanged(i);
            }
        }
        catch (Error e) {
            Toast.makeText(this, "An error has occurred. Please try again.", Toast.LENGTH_LONG).show();
        }
    }


}
