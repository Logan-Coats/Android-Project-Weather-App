package com.example.androidgroupprojectf22;

import android.util.Log;

import java.util.ArrayList;

public class hourlyModel {

    private ArrayList<hourlyData> hourlyArray= null;

    public ArrayList<hourlyData> getHourlyData() {
        return hourlyArray;
    }

    public void addHourlyData(String time, double temp, String condition) {
        this.hourlyArray.add(new hourlyData(time, temp, condition));
    }



    public static class hourlyData {
        private String time;
        private double temp;
        private String condition;

        public hourlyData(String time, double temp, String condition) {
            this.time = time;
            this.temp = temp;
            this.condition = condition;
        }

        public String getTime() {
            return this.time;
        }
        public double getTemp() {
            return this.temp;
        }
        public String getCondition() {
            return this.condition;
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
    }
}


