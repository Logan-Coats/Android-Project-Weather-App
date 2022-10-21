package com.example.androidgroupprojectf22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void testApi(View v){
        callApi(); // should log to console
    }
    private final String appid = "0ffb075f7e03483db28200427220310";
    private final String queryParams = "64468";
    private final String url = "https://api.weatherapi.com/v1/current.json?key="+appid+"&q="+queryParams;
    private String jsonResponse = "";

    public void callApi(){
        // This function will be in each activity that needs api access.
        // add code here to get current location IF text edit on search is empty OR if not passed a string for the call.


        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonResponse = response;
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
        //call handler class here

    }
    public void toWeek(View v){
        // TODO: if no text is entered GET the current location from the system, and pass it to the next activity
        // may need to be in lat,long format as a string

        // TODO: if location is not in the saved locations list, save it.
        // TODO: have a list of locations in a list/arraylist in the savedInstanceStateBundle, available to get at any time.
        Intent week = new Intent(this, WeekForecast.class);
        EditText locationET = findViewById(R.id.locationET);
        week.putExtra("Location", locationET.getText().toString());
        startActivity(week);
    }

   public void toMyLoc(View v){
        Intent myLocation = new Intent(this, WeekForecast.class);
        startActivity(myLocation);
    }

    public void toHistory(View v){
        Intent history = new Intent(this, activity_history.class);
        EditText locationET = findViewById(R.id.locationET);
        history.putExtra("Location", locationET.getText().toString());
        startActivity(history);
    }


    public void toHourly(View v) {
        Intent hourly = new Intent(this, hourly_Forecast.class);
        startActivity(hourly);
    }
}