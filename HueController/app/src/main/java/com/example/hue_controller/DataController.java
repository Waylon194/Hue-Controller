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
import java.util.Collections;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataController {
    private ArrayList<LampData> lamps;
    private Context context;
    private RequestQueue queue;
    private ILamp listener;

    public DataController(Context context, ILamp listener) {
        this.lamps = new ArrayList<>();
        this.context = context;
        this.listener = listener;
        queue = Volley.newRequestQueue(this.context);
    }

    public ArrayList<LampData> getLamps() {
        return lamps;
    }

    public void test(){
        ArrayList names = new ArrayList();
        names.add("Badkamer");
        names.add("Woonkamer");
        names.add("Slaapkamer");
        names.add("Hal");
        names.add("Douche");
        names.add("Schuur");
        names.add("WC");
        names.add("Keuken");
        names.add("Studeerkamer");
        names.add("Logeerkamer");
        names.add("Tuin Lamp");
        names.add("Balkon Lamp");

        Collections.shuffle(names);

        for (int i = 0; i < names.size(); i++){
            this.lamps.add(new LampData((int)(Math.random() * 65536), (int)(Math.random() * 255), (int)(Math.random() * 255), names.get(i) + " #" + (i + 1), true));
        }
    }

    public void updateLamp(LampData lamp){
        for (LampData l : this.lamps) {
            if(l.getId().equals(lamp.getId())){
                l.setValues(lamp);
                break;
            }
        }
    }

    public void getLampsLA136 (){
        final String url = "http://145.48.205.33/api/iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONObject("lights").names();
                    DataController.this.listener.onResponse(response, array);
                    Log.v(TAG, "Got: " + response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                Log.v(TAG, error.toString());
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void lamp(boolean state, int hue, int sat, int brightness) {
        final String partURl = "/lights/1/state";
        final String url = "http://145.48.205.33/api/iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB/lights/1/state";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("on", state);
            jsonObject.put("hue", hue);
            jsonObject.put("sat", sat);
            jsonObject.put("bri", brightness);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(
            Request.Method.PUT, url, jsonObject,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length() ; i++) {
                        try {
                            if (response.getJSONObject(i).getJSONObject("success").getBoolean(partURl)) {
                                //Show message
                                //Toast.makeText(context, "Successfully retrieved lamp", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
        );
        queue.add(request);
    }

    private String buildApiString(boolean state) {
        String userKey = "";
        String apiString = "/api/" + userKey + "/lights/" + state + "/state";
        Log.v(TAG, "apiString=" + apiString);
        return apiString;
    }

    private JSONObject buildBody(boolean state, int hue, int saturation, int brightness) {
        JSONObject body = new JSONObject();
        try {
            body.put("on", state);
            body.put("hue", hue);
            body.put("sat", saturation);
            body.put("bri", brightness);
        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }
        Log.v(TAG, "JSON body=" + body.toString());
        return body;
    }
}