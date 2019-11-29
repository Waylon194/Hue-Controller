package com.example.hue_controller.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hue_controller.DataController;
import com.example.hue_controller.IPair;
import com.example.hue_controller.R;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment implements IPair {
    private View view;
    private Context context;
    private Button pairBtn;
    private DataController dataController;
    private IPair instance;
    private EditText ipEditText;
    private EditText portEditText;
    private EditText userKeyEditText;
    private String ip;
    private String port;
    private String userKey;
    private final String ipTag = "IP";
    private final String portTag = "Port";
    private final String userKeyTag = "UserKey";
    private final String PREFS_NAME = "SETTINGS";

    public SettingsFragment() {
    }

    public void init(Context context){
        this.context = context;
        loadPreferences();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_settings, container, false);
        this.pairBtn = view.findViewById(R.id.pairButton);
        this.dataController = DataController.getInstance();
        this.instance = this;
        this.ipEditText = view.findViewById(R.id.ipText);
        this.portEditText = view.findViewById(R.id.portText);
        this.userKeyEditText = view.findViewById(R.id.userKeyText);
        this.ipEditText.setText(this.ip);
        this.portEditText.setText(this.port);
        this.userKeyEditText.setText(this.userKey);

        this.pairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataController.pair(instance);
            }
        });

        this.ipEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!ipEditText.getText().toString().trim().isEmpty()){
                        ip = ipEditText.getText().toString();
                        saveString(ipTag, ip);
                        dataController.refreshLamps();
                    }
                }
            }
        });

        this.portEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!portEditText.getText().toString().trim().isEmpty()){
                        port = (String) portEditText.getText().toString();
                        saveString(portTag, port);
                        dataController.refreshLamps();
                    }
                }
            }
        });

        this.userKeyEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!userKeyEditText.getText().toString().trim().isEmpty()){
                        userKey = (String) userKeyEditText.getText().toString();
                        saveString(userKeyTag, userKey);
                        dataController.refreshLamps();
                    }
                }
            }
        });
        return view;
    }

    public void loadPreferences() {
        SharedPreferences preferences = this.context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        this.ip = preferences.getString(this.ipTag, "145.48.205.33");
        this.port = preferences.getString(this.portTag, "80");
        this.userKey = preferences.getString(this.userKeyTag, "iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB");
    }

    private void saveString(String key, String value){
        SharedPreferences.Editor preferences = this.context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        preferences.putString(key, value);
        preferences.apply();
        Log.d("saveString", "Saved " + key + " with " + value);
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

    @Override
    public void onResponse(String userKey) {
        this.userKey = userKey;
        saveString(this.userKeyTag, this.userKey);
        this.userKeyEditText.setText(this.userKey);
    }
}