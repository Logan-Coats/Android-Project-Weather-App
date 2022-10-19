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
    public WeatherApiObj convertToObject(String jsonResponse){
        Gson gson = new Gson();
        WeatherApiObj w_api = gson.fromJson(jsonResponse, WeatherApiObj.class);
        return w_api;
    }
}
