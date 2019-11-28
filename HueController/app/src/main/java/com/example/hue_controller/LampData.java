package com.example.hue_controller;

import java.io.Serializable;
import java.util.Objects;

public class LampData implements Serializable {

    private int hue;
    private int saturation;
    private int brightness;
    private String lampName;
    private boolean state;

    public LampData(int hue, int saturation, int brightness, String lampName, boolean state) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        this.lampName = lampName;
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

    public String getLampName() {
        return lampName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LampData lampData = (LampData) o;
        return Objects.equals(lampName, lampData.lampName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lampName);
    }
}