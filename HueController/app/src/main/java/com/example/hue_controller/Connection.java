package com.example.hue_controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Connection {

    private static final String TAG = "Connection";
    private static final Connection INSTANCE = new Connection();

    private RequestQueue queue;
    private String ip;
    private String port;
    private String userKey;
    private ILamp listener;

    private Connection(){}

    public static Connection getInstance(){
        return INSTANCE;
    }

    public void connect(Context context, String ip, String port, String userKey, ILamp listener){
        this.queue = Volley.newRequestQueue(context);
        this.ip = ip;
        this.port = port;
        this.userKey = userKey;
        this.listener = listener;
    }

    public void getLamps(){
        String url = "http://" + this.ip + ":" + this.port + "/api/" + this.userKey;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONObject("lights").names();
                    listener.onResponse(response, array);
                    Log.v(TAG, "Got: " + response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, error.toString());
            }
        });
        this.queue.add(jsonObjectRequest);
    }

    public void putLamp(LampData lamp){
        CustomJsonArrayRequest request = new CustomJsonArrayRequest(
                Request.Method.PUT,
                buildUrl(lamp.getLampName()),
                buildBody(lamp),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "Response=" + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error=" + error.getMessage());
                    }
                }
        );
        this.queue.add(request);
    }

    private String buildUrl(String lightNumber){
        String url = "http://" + this.ip + ":" + this.port + buildApiString(lightNumber);
        Log.i(TAG, "URL=" + url);
        return url;
    }

    private String buildApiString(String lightNumber){
        String apiString = "/api/" + this.userKey + "/lights/" + lightNumber + "/state";
        Log.i(TAG, "apiString=" + apiString);
        return apiString;
    }

    private JSONObject buildBody(LampData lamp){
        JSONObject body = new JSONObject();
        try {
            body.put("on", lamp.getState());
            body.put("hue", lamp.getHue());
            body.put("sat", lamp.getSaturation());
            body.put("bri", lamp.getBrightness());
        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }
        Log.i(TAG, "JSON body=" + body.toString());
        return body;
    }
}
