package com.example.hue_controller;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataController {

    String userKey = "newdeveloper";
    String ip = "10.0.2.2";
    int port = 80;

    String fullAddress = ip + ":" + port;

    private ArrayList<LampData> lamps;

    private static final DataController dataController = new DataController();

    private DataController() {
        this.lamps = new ArrayList<>();
        test();
    }

    public static DataController getInstance(){
        return dataController;
    }

    public ArrayList<LampData> getLamps() {
        return lamps;
    }

    public void test(){
        for (int i = 0; i < 20; i++){
            this.lamps.add(new LampData((int)(Math.random() * 60000), (int)(Math.random() * 255), (int)(Math.random() * 255), "Lamp " + i, true));
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

    public void sendToHueBridge(LampData lamp) {
        String apiString = buildApiString(lamp.getState());
        JSONObject body = buildBody(lamp.getHue(), lamp.getSaturation(), lamp.getBrightness());

        // Note that the HUE API expects a JSONObject but returns a JSONArray,
        // hence the use of this custom Volley class that handles this
//        CustomJsonArrayRequest request = new CustomJsonArrayRequest(
//                Request.Method.PUT,
//                buildUrl(),
//                buildBody(),
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.i(TAG, "Response=" + response.toString());
//                        try {
//                            resultView.setText(response.toString(4));
//                        } catch (JSONException exception) {
//                            exception.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG, "Error=" + error.getMessage());
//                        resultView.setText(error.getMessage());
//                    }
//                }
//        );
//        Log.i(TAG, "Sending request");
//        requestQueue.add(request);
    }

    private String buildApiString(boolean state) {
        String apiString = "/api/" + userKey + "/lights/" + state + "/state";
        Log.i(TAG, "apiString=" + apiString);
        return apiString;
    }

    private JSONObject buildBody(int hue, int saturation, int brightness) {
        JSONObject body = new JSONObject();
        try {
            body.put("on", true);
            body.put("hue", hue);
            body.put("sat", saturation);
            body.put("bri", brightness);
        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }
        Log.i(TAG, "JSON body=" + body.toString());
        return body;
    }
}