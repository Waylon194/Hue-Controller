package com.example.hue_controller.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hue_controller.IPair;
import com.example.hue_controller.Model;
import com.example.hue_controller.R;

import org.json.JSONException;

public class SettingsFragment extends Fragment implements IPair {

    private Model model;
    private EditText ipEditText;
    private EditText portEditText;
    private EditText userKeyEditText;
    private Button pairBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        this.model = Model.getInstance();

        //Load the view items
        this.ipEditText = view.findViewById(R.id.ipText);
        this.portEditText = view.findViewById(R.id.portText);
        this.userKeyEditText = view.findViewById(R.id.userKeyText);
        this.pairBtn = view.findViewById(R.id.pairButton);

        //Set the view item values
        this.ipEditText.setText(this.model.getIp());
        this.portEditText.setText(this.model.getPort());
        this.userKeyEditText.setText(this.model.getUserKey());

        this.pairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.pair();
            }
        });

        this.ipEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!ipEditText.getText().toString().trim().isEmpty()) {
                        model.setIp(getContext(), ipEditText.getText().toString());
                        model.refreshLamps();
                    }
                }
            }
        });

        this.portEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!portEditText.getText().toString().trim().isEmpty()) {
                        model.setPort(getContext(), portEditText.getText().toString());
                        model.refreshLamps();
                    }
                }
            }
        });

        this.userKeyEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!userKeyEditText.getText().toString().trim().isEmpty()) {
                        model.setUserKey(getContext(), userKeyEditText.getText().toString());
                        model.refreshLamps();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onResponse(String userKey) {
        model.setUserKey(getContext(), userKey);
        this.userKeyEditText.setText(userKey);
    }

    @Override
    public void onError(JSONException e) {

    }
}