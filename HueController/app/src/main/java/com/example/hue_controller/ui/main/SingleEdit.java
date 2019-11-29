package com.example.hue_controller.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hue_controller.Connection;
import com.example.hue_controller.ILamp;
import com.example.hue_controller.LampAdapter;
import com.example.hue_controller.Lamp;
import com.example.hue_controller.Model;
import com.example.hue_controller.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SingleEdit extends Fragment implements ILamp {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private Model model;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_edit, container, false);
        this.model = Model.getInstance();
        this.adapter = new LampAdapter(this.model.getLamps());
        this.recyclerView = view.findViewById(R.id.singleRecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onResponse(JSONObject jsonObject, JSONArray lampNames) {
        JSONObject currentLampStateObject;

        List<String> lampNameList = new ArrayList<>();

        for (int i = 0; i < lampNames.length() ; i++) {
            try {
                lampNameList.add(lampNames.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (String s : lampNameList) {
            try {
                currentLampStateObject = jsonObject.getJSONObject("lights").getJSONObject(s).getJSONObject("state");

                Lamp lamp = new Lamp(
                        currentLampStateObject.getInt("hue"),
                        currentLampStateObject.getInt("sat"),
                        currentLampStateObject.getInt("bri"),
                        s,
                        jsonObject.getJSONObject("lights").getJSONObject(s).getString("name"),
                        currentLampStateObject.getBoolean("on")
                );
                this.model.addLamp(lamp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.adapter.notifyDataSetChanged();
    }
}