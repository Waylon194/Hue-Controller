package com.example.hue_controller;

import java.io.Serializable;

public class LampData implements Serializable {

    private int hue;
    private int saturation;
    private int brightness;
    private String id;
    private boolean state;

    public LampData(int hue, int saturation, int brightness, String id, boolean state) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        this.id = id;
        this.state = state;
    }

    public int getHue() {
        return hue;
    }

    public int getSaturation() {
        return saturation;
    }

    public int getBrightness() {
        return brightness;
    }

    public String getId() {
        return id;
    }

    public boolean getState() {
        return state;
    }


}
