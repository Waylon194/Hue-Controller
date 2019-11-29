package com.example.hue_controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hue_controller.ui.main.SettingsFragment;
import com.example.hue_controller.ui.main.SingleEdit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.AccessControlContext;

public class Connection {
    private static final String TAG = "Connection";
    private RequestQueue queue;

    private Context context;
    private ILamp iLamp;
    private IPair iPair;
    private Model model;

    public Connection(Context context, ILamp iLamp, IPair iPair) {
        this.queue = Volley.newRequestQueue(context);
        this.iLamp = iLamp;
        this.iPair = iPair;
        this.model = Model.getInstance();
    }

    public void getLamps(){
        String url = "http://" + this.model.getIp() + ":" + this.model.getPort() + "/api/" + this.model.getUserKey();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONObject("lights").names();
                    iLamp.onResponse(response, array);
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

    public void putLamp(Lamp lamp){
        CustomJsonArrayRequest request = new CustomJsonArrayRequest(
                Request.Method.PUT,
                buildUrl(lamp.getLampID()),
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
        String url = "http://" + this.model.getIp() + ":" + this.model.getPort() + buildApiString(lightNumber);
        Log.i(TAG, "URL=" + url);
        return url;
    }

    private String buildApiString(String lightNumber){
        String apiString = "/api/" + this.model.getUserKey() + "/lights/" + lightNumber + "/state";
        Log.i(TAG, "apiString=" + apiString);
        return apiString;
    }

    private JSONObject buildBody(Lamp lamp){
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

    public void pair(){
        String url = "http://" + this.model.getIp() + ":" + this.model.getPort() + "/api/";

        JSONObject jobject = new JSONObject();
        try{
            jobject.put("devicetype", "MijnHueApp");
        } catch (JSONException e){
            e.printStackTrace();
        }

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(
                Request.Method.POST,
                url,
                jobject,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            String userKey = response.getJSONObject(0).getJSONObject("success").getString("username");
                            iPair.onResponse(userKey);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
}