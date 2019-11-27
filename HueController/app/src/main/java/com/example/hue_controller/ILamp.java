package com.example.hue_controller;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ILamp {
    void onResponse (JSONObject jsonObject, JSONArray lampNames);
}