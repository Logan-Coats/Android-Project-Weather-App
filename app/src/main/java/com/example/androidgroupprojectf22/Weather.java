package com.example.androidgroupprojectf22;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class Weather {
    public WeatherAPIConstants convertToObject(String jsonResponse){
        WeatherAPIConstants weather = new WeatherAPIConstants();
        try{
            JSONObject weatherObj = new JSONObject(jsonResponse);
            JSONObject currentObj = new JSONObject(weatherObj.getString("current"));
            weather.current.cloud = currentObj.getInt("cloud");
            weather.current.feels_like_c = currentObj.getDouble("feels_like_c");
            weather.current.feels_like_f = currentObj.getDouble("feels_like_f");
        } catch (JSONException err){
            Log.d("Error",err.toString());
        }




        return null;
    }
}
