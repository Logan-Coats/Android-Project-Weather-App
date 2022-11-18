package com.example.androidgroupprojectf22;

import androidx.annotation.NonNull;
import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationClient;
    private double lat;
    private double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if(location == null){
                            LocationRequest mLocationRequest = new LocationRequest();
                            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                            mLocationRequest.setInterval(5);
                            mLocationRequest.setFastestInterval(0);
                            mLocationRequest.setNumUpdates(1);

                            //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                            fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        } else {
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }
                });
            }else{
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }
    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
        }
    };
    public void toWeek(View v){


        // TODO: if location is not in the saved locations list, save it. do this in weekly so that the name is finalized.
        // TODO: have a list of locations in a list/arraylist in the savedInstanceStateBundle, available to get at any time.
        Intent week = new Intent(this, WeekForecast.class);
        EditText locationET = findViewById(R.id.locationET);
        if(locationET.getText().toString().isEmpty()){
            // TODO: if no text is entered GET the current location from the system, and pass it to the next activity
            // may need to be in lat,long format as a string
        }
        week.putExtra("Location", locationET.getText().toString());
        startActivity(week);
    }

   public void toMyLoc(View v){
        Intent myLocation = new Intent(this, WeekForecast.class);
        myLocation.putExtra("Location", lat+","+lon);
        startActivity(myLocation);
    }

    public void toHistory(View v){
        Intent history = new Intent(this, activity_history.class);
        EditText locationET = findViewById(R.id.locationET);
        history.putExtra("Location", locationET.getText().toString());
        startActivity(history);
    }
}