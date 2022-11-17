package com.example.androidgroupprojectf22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WeekForecast extends AppCompatActivity { //weeklyforecast is the 'Daily' activity, as it will show the weather for the next few days.
    private String appid = "0ffb075f7e03483db28200427220310";
    private String location = "64468";
    private String url = "https://api.weatherapi.com/v1/forecast.json?key="+appid+"&q=";
    private String jsonResponse = "";
    private WeatherApiObj forecast = new WeatherApiObj();
    private Weather forecastHelper = new Weather();
    private WeeklyAdapter weeklyServer = null;
    RecyclerView weeklyRecycler = null;
    TextView currTemp;
    TextView currWeather;
    TextView currLoc;
    ImageView currWeatherIMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_forecast);

        Intent week = getIntent();
        location = week.getStringExtra("Location");
        currTemp = findViewById(R.id.currTemp);
        currWeather = findViewById(R.id.currWeather);
        currLoc = findViewById(R.id.currLoc);
        currWeatherIMG = findViewById(R.id.currWeatherIMG);

        weeklyServer = new WeeklyAdapter();
        weeklyRecycler = findViewById(R.id.weeklyRecyclerView);
        weeklyRecycler.setAdapter(weeklyServer);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        weeklyRecycler.setLayoutManager(myManager);
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
                currLoc.setText(forecast.location.name + ", "+ forecast.location.region+", "+forecast.location.country);
                ArrayList<WeeklyModel.WeeklyData> tempData = new ArrayList<>();
                for(int i = 0; i<5;i++){
                    tempData.add(new WeeklyModel.WeeklyData(forecast.forecast.forecastday.get(i).day.mintemp_f,
                            forecast.forecast.forecastday.get(i).day.maxtemp_f,
                            forecast.forecast.forecastday.get(i).date));
                }
                WeeklyModel.getModel().addWeeklyData(tempData);
                weeklyServer.notifyItemRangeChanged(0,5);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.toString().trim());
                toSearch(findViewById(R.id.searchBT));
                //upon error, take user back to search screen.
            }
        });
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(req);
    }
/*
    private void loadImage(String url) {
        Picasso.get()
                .load(url)
                .into(currWeatherIMG);
    }
 */
    public void toSearch(View v){
        Intent search = new Intent(this, MainActivity.class);
        startActivity(search);
    }

    public void toHourly(View v){
        Intent hourly = new Intent(this, hourly_Forecast.class);
        hourly.putExtra("Location", location);
        startActivity(hourly);
    }

}