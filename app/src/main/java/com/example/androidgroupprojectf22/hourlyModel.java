package com.example.androidgroupprojectf22;

import android.util.Log;

import java.util.ArrayList;

public class hourlyModel {

    private ArrayList<hourlyData> hourlyArray= null;

    public ArrayList<hourlyData> getHourlyData() {
        return hourlyArray;
    }

    public void addHourlyData(String condition, int temp) {
        this.hourlyArray.add(new hourlyData(condition, temp));
    }



    public static class hourlyData {
        private String condition;
        private int temp;

        public hourlyData(String condition, int temp) {
            this.condition = condition;
            this.temp = temp;
        }

        public String getCondition() {
            return this.condition;
        }
        public int getTemp() {
            return this.temp;
        }
    }

    private hourlyModel() {
        this.hourlyArray = new ArrayList<hourlyData>();
        loadModel();

    }

    private static hourlyModel model = null;
    public static hourlyModel getSingleton() {
        if (model == null) {
            model = new hourlyModel();
        }
        return model;
    }

    private void loadModel() {
        Log.d("test","test");
        addHourlyData("Rainy", 60);
        addHourlyData("Sunny", 90);
        addHourlyData("Cloudy", 75);
        addHourlyData("Rainy", 40);
        addHourlyData("Snow", 10);
        addHourlyData("Sunny", 80);
        Log.d("test", "yo");

    }
}


