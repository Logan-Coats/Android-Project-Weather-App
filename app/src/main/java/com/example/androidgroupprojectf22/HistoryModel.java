package com.example.androidgroupprojectf22;

import java.util.ArrayList;

public class HistoryModel {

    public static class HistoryData {
        private String location;
        private String temp;

        public HistoryData(String location, String temp) {
            this.location = location;
            this.temp = temp;
        }
        public String getLocation() {
            return location;
        }
        public String getTemp() {
            return temp;
        }
    }

    private ArrayList<HistoryData> historyList = null;

    private HistoryModel() {
        historyList = new ArrayList<HistoryData>();
    }

    public ArrayList<HistoryData> getHistoryList() {
        return historyList;
    }

    public void addHistoryData(String location, String temp) {
        this.historyList.add(new HistoryData(location, temp));
    }

    public String getHistoryLoc(int i) {
        return this.historyList.get(i).location;
    }

    public void resetList() {
        this.historyList = new ArrayList<HistoryData>();
    }

    private static HistoryModel model = null;
    public static HistoryModel getModel(){
        if(model == null){
            model = new HistoryModel();
        }
        return model;
    }
}
