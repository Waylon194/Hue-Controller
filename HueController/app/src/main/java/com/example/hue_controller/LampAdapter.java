package com.example.hue_controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.LampViewHolder> {

    private ArrayList<LampData> lamps;
    private boolean singleLamp;

    public LampAdapter(ArrayList<LampData> lamps, boolean singleLamp) {
        this.lamps = lamps;
        this.singleLamp = singleLamp;
    }

    @NonNull
    @Override
    public LampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if(this.singleLamp){
            view = inflater.inflate(R.layout.activity_row_item, parent, false);
        } else {
            view = inflater.inflate(R.layout.activity_row_item_multiple, parent, false);
        }

        return new LampViewHolder(view);
    }

    //Load selected data into row item
    @Override
    public void onBindViewHolder(@NonNull LampViewHolder viewHolder, int position) {
        final LampData lampData = this.lamps.get(position);
        viewHolder.lampID.setText(lampData.getId());
    }

    @Override
    public int getItemCount() {
        return lamps.size();
    }

    public class LampViewHolder extends RecyclerView.ViewHolder {
        private TextView lampID;

        public LampViewHolder(@NonNull final View view) {
            super(view);
            if(singleLamp){
                this.lampID = view.findViewById(R.id.lampNameSingle);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), LampDetailed.class);
                        LampData lamp = lamps.get(LampViewHolder.super.getAdapterPosition());
                        intent.putExtra("LAMP", lamp);
                        v.getContext().startActivity(intent);
                    }
                });

            } else {
                this.lampID = view.findViewById(R.id.lampNameMultiple);


            }
        }
    }
}
