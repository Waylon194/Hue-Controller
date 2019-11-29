package com.example.hue_controller;

import org.json.JSONException;
import org.json.JSONObject;

public interface IPair {
    void onResponse(String userKey);
    void onError(JSONException e);
}
