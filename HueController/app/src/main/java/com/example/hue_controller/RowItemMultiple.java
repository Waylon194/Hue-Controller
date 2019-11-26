package com.example.hue_controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

public class RowItemMultiple extends AppCompatActivity {

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_item_multiple);

        this.checkBox = findViewById(R.id.checkBox);
    }
}
