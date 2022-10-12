package com.example.androidgroupprojectf22;

import com.google.gson.Gson;


public class Weather {
    public WeatherApiObj convertToObject(String jsonResponse){
        Gson gson = new Gson();
        WeatherApiObj w_api = gson.fromJson(jsonResponse, WeatherApiObj.class);
        return w_api;
    }
}
