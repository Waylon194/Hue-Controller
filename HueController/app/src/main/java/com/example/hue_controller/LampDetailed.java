package com.example.hue_controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class LampDetailed extends AppCompatActivity {

    private TextView lampName;
    private SeekBar hue;
    private SeekBar saturation;
    private SeekBar brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_detailed);

        this.lampName = findViewById(R.id.lampNameDetailed);
        this.hue = findViewById(R.id.hueValue);
        this.saturation = findViewById(R.id.saturationValue);
        this.brightness = findViewById(R.id.brightnessValue);

        LampData lamp = (LampData) getIntent().getSerializableExtra("LAMP");
        this.lampName.setText(lamp.getId());
        this.hue.setProgress(lamp.getHue());
        this.saturation.setProgress(lamp.getSaturation());
        this.brightness.setProgress(lamp.getBrightness());
    }
}
