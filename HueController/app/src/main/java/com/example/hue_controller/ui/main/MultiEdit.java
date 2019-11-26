package com.example.hue_controller.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hue_controller.DataController;
import com.example.hue_controller.LampAdapter;
import com.example.hue_controller.R;

public class MultiEdit extends Fragment{

    private View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_multi_edit, container, false);
        this.recyclerView = this.view.findViewById(R.id.multiRecyclerView);
        this.adapter = new LampAdapter(DataController.getInstance().getLamps(), false);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    //Update all the lamps in the recyclerview
    public void updateLamps() {
        this.adapter.notifyDataSetChanged();
    }
}
