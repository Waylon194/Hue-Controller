package com.example.hue_controller.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hue_controller.R;
import com.example.hue_controller.SingleLampAdapter;

public class SingleEdit extends Fragment{

    private View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_single_edit, container, false);
//        this.recyclerView = view.findViewById(R.id.singleRecyclerView);
//        this.adapter = new SingleLampAdapter(this.logicController.getDwarves());
//        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        this.recyclerView.setAdapter(adapter);
        return view;
    }

    //Update all the dwarves in the recyclerview
    public void updateDwarfes() {
        this.adapter.notifyDataSetChanged();
    }
}
