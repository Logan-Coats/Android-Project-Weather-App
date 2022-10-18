package com.example.androidgroupprojectf22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class WeekForecast extends AppCompatActivity { //weeklyforecast is the 'Daily' activity, as it will show the weather for the next few days.
    private String appid = "0ffb075f7e03483db28200427220310";
    private String location = "64468";
    private String url = "https://api.weatherapi.com/v1/current.json?key="+appid+"&q=";
    private String jsonResponse = "";
    private WeatherApiObj forecast = new WeatherApiObj();
    private Weather forecastHelper = new Weather();
    TextView currTemp;
    TextView currWeather;
    ImageView currWeatherIMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_forecast);

        Intent week = getIntent();
        location = week.getStringExtra("Location");
        currTemp = findViewById(R.id.currTemp);
        currWeather = findViewById(R.id.currWeather);
        currWeatherIMG = findViewById(R.id.currWeatherIMG);
        Weather forecastHelper = new Weather();
        callApi(location);

    }
    public void callApi(String loc) {
        if (loc == "" || loc == null) {
            //get current location
        } else {
            url = url.concat(loc);
            url = url.concat("&days=5&aqi=no&alerts=no");
        }

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonResponse = response;
                forecast = forecastHelper.convertToObject(jsonResponse);
                currTemp.setText(String.valueOf(forecast.current.temp_f));
                currWeather.setText(String.valueOf(forecast.current.condition.text));
                currWeatherIMG.setImageURI(Uri.parse(forecast.current.condition.icon));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.toString().trim());
            }
        });
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(req);
    }
    public void toSearch(View v){
        Intent search = new Intent(this, MainActivity.class);
        startActivity(search);
    }

}