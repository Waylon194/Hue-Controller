package com.example.hue_controller;

import java.io.Serializable;

public class LampData implements Serializable {

    private int r;
    private int g;
    private int b;
    private String id;
    private boolean on;

    public LampData(int r, int g, int b, String id, boolean on) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.id = id;
        this.on = on;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public String getId() {
        return id;
    }

    public boolean isOn() {
        return on;
    }


}
