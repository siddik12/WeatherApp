package com.example.rasel.weatherreport.model;

public class Demo {
    private String today;
    private String min;
    private String max;
    private String date;
    private int icon;

    public Demo(String today, String min, String max, String date, int icon) {
        this.today = today;
        this.min = min;
        this.max = max;
        this.date = date;
        this.icon = icon;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
