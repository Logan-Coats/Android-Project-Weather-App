package com.example.androidgroupprojectf22;

import java.util.ArrayList;

public class hourlyModel {

    private ArrayList<hourlyData> hourlyData= null;

    public ArrayList<hourlyData> getHourlyData() {
        return hourlyData;
    }
    public void addHourlyData(String condition, int temp) {
        this.hourlyData.add(new hourlyData(condition, temp));
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
        this.hourlyData = new ArrayList<hourlyData>();

    }

    private static hourlyModel model = null;
    public static hourlyModel getModel() {
        if (model == null) {
            model = new hourlyModel();
        }
        return model;
    }

    private void loadModel() {
        addHourlyData("Rainy", 60);
        addHourlyData("Sunny", 90);
        addHourlyData("Cloudy", 75);
    }
}


