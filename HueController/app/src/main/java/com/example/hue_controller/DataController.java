package com.example.hue_controller;

import android.content.Context;
import android.util.Log;

import com.example.hue_controller.ui.main.SettingsFragment;

import java.util.ArrayList;

public class DataController {
    private final String TAG = "DataController";
    private ArrayList<LampData> lamps;
    private Context context;
    private ILamp listener;
    private Connection connection;
    private SettingsFragment settings;
    private final static DataController instance = new DataController();

    private DataController(){
        this.lamps = new ArrayList<>();
    }

    public static DataController getInstance(){
        return instance;
    }

    public void init(Context context, ILamp listener, SettingsFragment settings) {
        this.context = context;
        this.listener = listener;
        this.settings = settings;
    }

    public void connect(){
        this.connection = Connection.getInstance();
        this.connection.connect(this.context, this.settings, this.listener);
    }

    public void updateLamp(LampData lamp){
        for (LampData l : this.lamps) {
            if(l.equals(lamp)){
                l.setValues(lamp);
                this.connection.putLamp(lamp);
                Log.d(TAG, "updateLamp");
                break;
            }
            Log.d(TAG, "updateLamp");
        }
    }

    public void refreshLamps(){
        this.lamps.clear();
        this.connection.getLamps();
    }

    public ArrayList<LampData> getLamps() {
        return this.lamps;
    }

    public void addLamp(LampData lampData) {
        this.lamps.add(lampData);
    }

    public void pair(IPair pair){
        this.connection.pair(pair);
    }
}