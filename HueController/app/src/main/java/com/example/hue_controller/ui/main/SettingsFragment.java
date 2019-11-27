package com.example.hue_controller.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hue_controller.R;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    private View view;
    private Context context;

    private static final String PREFS_NAME = "SETTINGS";

    public SettingsFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_settings, container, false);
        loadPreferences();

        return view;
    }

    private void loadPreferences() {
        SharedPreferences preferences = this.context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }
}
