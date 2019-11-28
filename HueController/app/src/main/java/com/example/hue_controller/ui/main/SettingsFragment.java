package com.example.hue_controller.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.hue_controller.DataController;
import com.example.hue_controller.R;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {
    private View view;
    private Context context;
    private Switch emuSwitch;
    private EditText ipEditText;
    private EditText portEditText;
    private EditText userKeyEditText;
    private Boolean emulating;
    private String ip;
    private String port;
    private String userKey;
    private static final String emultatingTag = "Emulating";
    private static final String ipTag = "IP";
    private static final String portTag = "Port";
    private static final String userKeyTag = "UserKey";
    private static final String PREFS_NAME = "SETTINGS";

    public SettingsFragment(Context context) {
        this.context = context;
        loadPreferences();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_settings, container, false);

        this.emuSwitch = view.findViewById(R.id.bridgeEmulatorSwitch);
        this.ipEditText = view.findViewById(R.id.ipText);
        this.portEditText = view.findViewById(R.id.portText);
        this.userKeyEditText = view.findViewById(R.id.userKeyText);

        this.emuSwitch.setChecked(emulating);
        this.ipEditText.setText(this.ip);
        this.portEditText.setText(this.port);
        this.userKeyEditText.setText(this.userKey);

        this.emuSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emulating = isChecked;
                saveBoolean(emultatingTag, emulating);
                DataController.getInstance().refreshLamps();
            }
        });

        this.ipEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!ipEditText.getText().toString().trim().isEmpty()){
                        ip = ipEditText.getText().toString();
                        saveString(ipTag, ip);
                        DataController.getInstance().refreshLamps();
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
                        DataController.getInstance().refreshLamps();
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
                        DataController.getInstance().refreshLamps();
                    }
                }
            }
        });
        return view;
    }

    public void loadPreferences() {
        SharedPreferences preferences = this.context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        this.emulating = preferences.getBoolean(this.emultatingTag, true);
        this.ip = preferences.getString(this.ipTag, "145.48.205.33");
        this.port = preferences.getString(this.portTag, "80");
        this.userKey = preferences.getString(this.userKeyTag, "iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB");
    }

    private void saveBoolean(String key, boolean value){
        SharedPreferences.Editor preferences = this.context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        preferences.putBoolean(key, value);
        preferences.apply();
        Log.d("saveBoolean", "Saved " + key + " with " + value);
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
}