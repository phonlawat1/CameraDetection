package com.example.myapplication;

public class Profile {
    private String Name;
    private String timeText;


    public Profile(String timeText){
        this.timeText = timeText;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }
}
