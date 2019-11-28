package com.example.hue_controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.LampViewHolder> implements ILamp {
    private ArrayList<LampData> lamps;

    public LampAdapter(ArrayList<LampData> lamps) {
        this.lamps = lamps;
    }

    @NonNull
    @Override
    public LampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        view = inflater.inflate(R.layout.activity_row_item, parent, false);
        return new LampViewHolder(view);
    }

    //Load selected data into row item
    @Override
    public void onBindViewHolder(@NonNull LampViewHolder viewHolder, int position) {
        final LampData lampData = this.lamps.get(position);
        viewHolder.lampName.setText(lampData.getLampName());
    }

    @Override
    public int getItemCount() {
        return lamps.size();
    }

    @Override
    public void onResponse(JSONObject jsonObject, JSONArray lampNames) {
        // implement the method to parse the JSONObject response to a individual lamp object
    }

    public class LampViewHolder extends RecyclerView.ViewHolder {
        private TextView lampName;

        public LampViewHolder(@NonNull final View view) {
            super(view);
            this.lampName = view.findViewById(R.id.lampNameSingle);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), LampDetailed.class);
                    LampData lamp = lamps.get(LampViewHolder.super.getAdapterPosition());
                    intent.putExtra("LAMP", lamp);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}