package com.example.androidgroupprojectf22;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


public class Weather {
    private final String appid = "0ffb075f7e03483db28200427220310";
    private  String url = "https://api.weatherapi.com/v1/current.json?key="+appid+"&q=";
    private String jsonResponse = "";
    public WeatherApiObj convertToObject(String jsonResponse){
        Gson gson = new Gson();
        WeatherApiObj w_api = gson.fromJson(jsonResponse, WeatherApiObj.class);
        return w_api;
    }
}
