package com.example.hue_controller;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hue_controller.ui.main.SettingsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataController {
    private final String TAG = "DataController";

    private ArrayList<LampData> lamps;
    private Context context;
    private RequestQueue queue;
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
        this.connection.connect(this.context, this.settings.getIp(), this.settings.getPort(), this.settings.getUserKey(), this.listener);
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

    public ArrayList<LampData> getLamps() {
        return this.lamps;
    }

    public void addLamp(LampData lampData) {
        this.lamps.add(lampData);
    }
}