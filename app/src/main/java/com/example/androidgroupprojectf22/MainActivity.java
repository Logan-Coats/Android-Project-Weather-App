package com.example.androidgroupprojectf22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
    }

    public void testApi(View v){
        callApi(); // should log to console
    }
    private final String appid = "0ffb075f7e03483db28200427220310";
    private final String location = "64468";
    private final String url = "https://api.weatherapi.com/v1/current.json?key="+appid+"&q="+location;
    private String jsonResponse = "";

    public void callApi(){

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonResponse = response;
                Log.d("Json",jsonResponse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG" , error.toString().trim());
            }
        });
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(req);
        deserialize_json();
    }

    public void deserialize_json(){
        if(jsonResponse == "") return;
        //WeatherAPIConstants weather = gson.fromJson(jsonResponse,WeatherAPIConstants.class) ;
        try{
            JSONObject weather = new JSONObject(jsonResponse);
            JSONObject current = new JSONObject(weather.getString("current"));
            Log.d("Tag", ""+ weather.get("current"));
            Log.d("Tag", ""+ current.get("temp_f"));
        } catch(JSONException err){
            Log.d("Error",err.toString());
        }

    }
}