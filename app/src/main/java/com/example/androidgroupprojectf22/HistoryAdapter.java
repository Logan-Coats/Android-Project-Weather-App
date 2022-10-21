package com.example.androidgroupprojectf22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_cell, parent, false);
        HistoryViewHolder vh = new HistoryViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        TextView locTV = holder.itemView.findViewById(R.id.locTV);
        locTV.setText(model.getHistoryList().get(position).getLocation().toString());

        TextView tempTV = holder.itemView.findViewById(R.id.tempTV);
        tempTV.setText(model.getHistoryList().get(position).getTemp().toString());
    }

    @Override
    public int getItemCount() {
        return model.getHistoryList().size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public HistoryViewHolder(View v) {
            super(v);
        }
    }

    private HistoryModel model = HistoryModel.getModel();
    public HistoryAdapter(HistoryModel model) {
        super();
    }
}
