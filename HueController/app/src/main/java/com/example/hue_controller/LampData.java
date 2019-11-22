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

    public void setHue(int hue) {
        this.hue = hue;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setValues(LampData lampData){
        this.hue = lampData.getHue();
        this.saturation = lampData.getSaturation();
        this.brightness = lampData.getBrightness();
        this.state = lampData.getState();
    }
}
