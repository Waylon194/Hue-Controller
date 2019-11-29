package com.example.hue_controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class LampDetailed extends AppCompatActivity {
    private TextView lampName;
    private SeekBar hue;
    private SeekBar saturation;
    private SeekBar brightness;
    private Switch powerSwitch;
    private Lamp lamp;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_detailed);

        this.model = Model.getInstance();

        this.lampName = findViewById(R.id.lampNameDetailed);
        this.hue = findViewById(R.id.hueValue);
        this.saturation = findViewById(R.id.saturationValue);
        this.brightness = findViewById(R.id.brightnessValue);
        this.powerSwitch = findViewById(R.id.lampSwitch);

        this.lamp = (Lamp) getIntent().getSerializableExtra("LAMP");
        this.lampName.setText(lamp.getLampName());
        this.hue.setProgress(lamp.getHue());
        this.saturation.setProgress(lamp.getSaturation());
        this.brightness.setProgress(lamp.getBrightness());
        this.powerSwitch.setChecked(lamp.getState());

        this.hue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lamp.setHue(progress);
                model.updateLamp(lamp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.saturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lamp.setSaturation(progress);
                model.updateLamp(lamp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lamp.setBrightness(progress);
                model.updateLamp(lamp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.powerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lamp.setState(isChecked);
                model.updateLamp(lamp);
            }
        });
    }
}