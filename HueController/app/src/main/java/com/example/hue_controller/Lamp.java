package com.example.hue_controller;

import java.io.Serializable;
import java.util.Objects;

public class Lamp implements Serializable {

    private int hue;
    private int saturation;
    private int brightness;
    private String lampID;
    private String lampName;
    private boolean state;

    public Lamp(int hue, int saturation, int brightness, String lampID, String lampName, boolean state) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        this.lampID = lampID;
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

    public String getLampID() {
        return lampID;
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

    public void setValues(Lamp lamp){
        this.hue = lamp.getHue();
        this.saturation = lamp.getSaturation();
        this.brightness = lamp.getBrightness();
        this.state = lamp.getState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lamp lamp = (Lamp) o;
        return Objects.equals(lampID, lamp.lampID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lampID);
    }
}