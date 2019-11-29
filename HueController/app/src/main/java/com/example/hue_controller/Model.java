package com.example.hue_controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Model {

    private static final String TAG = "MODEL";
    private String ip;
    private String port;
    private String userKey;
    private ArrayList<Lamp> lamps;
    private Connection connection;
    private final String ipTag = "IP";
    private final String portTag = "Port";
    private final String userKeyTag = "UserKey";
    private final String PREFS_NAME = "SETTINGS";
    private static final Model INSTANCE = new Model();

    private Model(){
        this.lamps = new ArrayList<>();
    }

    public static Model getInstance() {
        return INSTANCE;
    }

    public void init(Context context, Connection connection) {
        this.connection = connection;
        this.ip = loadString(context, this.ipTag, "145.48.205.33");
        this.port = loadString(context, this.portTag, "80");
        this.userKey = loadString(context, this.userKeyTag, "iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB");
    }

    public void saveString(Context context, String key, String value) {
        SharedPreferences.Editor preferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        preferences.putString(key, value);
        preferences.apply();
        Log.d("saveString", "Saved " + key + " with " + value);
    }

    private String loadString(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getString(key, defaultValue);
    }

    public void pair(){
        this.connection.pair();
    }

    public ArrayList<Lamp> getLamps(){
        return lamps;
    }

    public void clearLamp(){
        this.lamps.clear();
    }

    public void refreshLamps(){
        this.lamps.clear();
        this.connection.getLamps();
    }

    public void addLamp(Lamp lamp) {
        this.lamps.add(lamp);
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setIp(Context context, String ip) {
        this.ip = ip;
        saveString(context, this.ipTag, ip);
    }

    public void setPort(Context context, String port) {
        this.port = port;
        saveString(context, this.portTag, port);
    }

    public void setUserKey(Context context, String userKey) {
        this.userKey = userKey;
        saveString(context, this.userKeyTag, userKey);
    }

    public void updateLamp(Lamp lamp) {
        for (Lamp l : this.lamps) {
            if(l.equals(lamp)){
                l.setValues(lamp);
                this.connection.putLamp(lamp);
                Log.d(TAG, "updateLamp");
                break;
            }
            Log.d(TAG, "updateLamp");
        }
    }
}