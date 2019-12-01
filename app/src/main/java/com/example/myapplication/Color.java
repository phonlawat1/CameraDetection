package com.example.myapplication;

import org.opencv.core.Mat;

public class Color {
    private double matt;

    public Color(double matt){
        this.matt = matt;

    }

    public double getMatt() {
        return matt;
    }

    public void setMatt(double matt) {
        this.matt = matt;
    }
}
