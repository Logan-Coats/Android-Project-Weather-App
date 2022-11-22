package com.example.androidgroupprojectf22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class activity_history extends AppCompatActivity
implements HistoryDF.HistorySelectionCallbacks {

    private String appid = "0ffb075f7e03483db28200427220310";
    private String url = "https://api.weatherapi.com/v1/current.json?key="+appid+"&q=";
    private WeatherApiObj weather = new WeatherApiObj();
    private Weather forecastHelper = new Weather();
    private String region;
    private List<String> currentTemps = new ArrayList<>();
    final List<ParseObject> result = new ArrayList<ParseObject>();
    private String location = "64468";
    private Integer position;


    public void callApi(String loc) {
        url = url.concat(loc);
        url = url.concat("&aqi=no&alerts=no");

        ParseObject temps = new ParseObject("History");

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                weather = forecastHelper.convertToObject(response);
                region = weather.location.region;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Temp", error.getMessage());
            }
        });
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(req);
    }

    public void toSearch(View v){
        Intent search = new Intent(this, MainActivity.class);
        startActivity(search);
    }

    @Override
    public void positiveAction() {

        RecyclerView historyRecycler = findViewById(R.id.historyRV);
        historyRecycler.setAdapter(historyServer);


        if (result.size() > 0) {
            ParseObject item = result.get(position);
            result.remove(position);
            item.deleteEventually(new DeleteCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("Parse", "Deleted Successfully");
                    } else {
                        Log.d("Parse", e.getMessage());
                    }
                }
            });
        }
        Log.d("Dialog", "Positive");
    }

    @Override
    public void negativeAction() {
        Log.d("Dialog", "Negative");
    }


    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
//            super.onLongPress(e);
            RecyclerView historyRV = findViewById(R.id.historyRV);
            View view = historyRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = historyRV.getChildViewHolder(view);
                if (holder instanceof HistoryAdapter.HistoryViewHolder) {
                    position = holder.getAdapterPosition();
                    // Long press
                    Log.d("Click", "Long press on item " + position);
                    HistoryDF historyDF = new HistoryDF();
                    historyDF.show(getSupportFragmentManager(), "HISTORY");
                }
                historyServer.notifyDataSetChanged();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        RecyclerView historyRecycler = findViewById(R.id.historyRV);
        historyRecycler.setAdapter(historyServer);

        Intent history = getIntent();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("History");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    model.resetList();
                                        for(int i = 0; i < objects.size(); i ++) {
                        result.add(objects.get(i));
                        Log.d("Temp", result.get(i).get("loc") + " " + result.get(i).get("temp"));
                        model.addHistoryData(result.get(i).get("loc").toString(), result.get(i).get("temp").toString());
                    }

                    historyServer.notifyDataSetChanged();

                } else {
                    Log.d("Parse", e.getMessage());
                }
            }
        });

        LinearLayoutManager myManager = new LinearLayoutManager(this);
        historyRecycler.setLayoutManager(myManager);

        GestureDetectorCompat detector = new GestureDetectorCompat(this,
                new RecyclerViewOnGestureListener());
        historyRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });

    }

    private HistoryModel model = HistoryModel.getModel();
    HistoryAdapter historyServer = new HistoryAdapter(HistoryModel.getModel());
}