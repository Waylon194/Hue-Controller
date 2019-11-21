package com.example.hue_controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SingleLampImageAdapter extends RecyclerView.Adapter<SingleLampImageAdapter.SLViewHolder> {

    private ArrayList<LampData> lamps;

    public SingleLampImageAdapter(ArrayList<LampData> lamps) {
        this.lamps = lamps;
    }

    @NonNull
    @Override
    public SLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_row_item, parent, false);
        return new SLViewHolder(view);
    }

    //Load selected data into row item
    @Override
    public void onBindViewHolder(@NonNull SLViewHolder viewHolder, int position) {
        final LampData lampData = this.lamps.get(position);
        viewHolder.lampID.setText(lampData.getId());
    }

    @Override
    public int getItemCount() {
        return lamps.size();
    }

    public class SLViewHolder extends RecyclerView.ViewHolder {
        private TextView lampID;

        public SLViewHolder(@NonNull final View view) {
            super(view);
            this.lampID = view.findViewById(R.id.lampNameSingle);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), LampDetailed.class);
                    LampData lamp = lamps.get(SLViewHolder.super.getAdapterPosition());
                    intent.putExtra("LAMP", lamp);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
