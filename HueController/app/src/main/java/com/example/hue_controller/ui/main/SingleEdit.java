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

import com.example.hue_controller.DataController;
import com.example.hue_controller.R;
import com.example.hue_controller.LampAdapter;

public class SingleEdit extends Fragment{

    private View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_single_edit, container, false);
        this.recyclerView = this.view.findViewById(R.id.singleRecyclerView);
        this.adapter = new LampAdapter(DataController.getInstance().getLamps(), true);
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
