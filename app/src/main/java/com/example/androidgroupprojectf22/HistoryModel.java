package com.example.androidgroupprojectf22;

import java.util.ArrayList;

public class HistoryModel {

    public static class History {
        private String location;
        private Double temp;

        public History(String location, Double temp) {
            this.location = location;
            this.temp = temp;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }
    }

    private ArrayList<History> historyList = null;

    private HistoryModel() {
        historyList = new ArrayList<History>();
    }

    public ArrayList<History> getHistoryList() {
        return historyList;
    }

    // Make it a singleton
    private static HistoryModel model = null;
    public static HistoryModel getModel() {
        if(model == null) {
            model = new HistoryModel();
        }
        return model;
    }
}
