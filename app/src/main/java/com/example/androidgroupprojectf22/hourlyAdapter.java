package com.example.androidgroupprojectf22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class hourlyAdapter extends RecyclerView.Adapter<hourlyAdapter.hourlyViewHolder> {
    public static class hourlyViewHolder extends RecyclerView.ViewHolder {
        public hourlyViewHolder(View v) {
            super(v);
        }
    }

    public hourlyAdapter() {
        super();
    }

    private hourlyModel model = hourlyModel.getSingleton();

    public hourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_cell,parent,false);
        hourlyAdapter.hourlyViewHolder vh = new hourlyAdapter.hourlyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull hourlyAdapter.hourlyViewHolder holder, int position){
        TextView hourlyTemp = holder.itemView.findViewById(R.id.hourlyTempTV);
        TextView hourlyCond = holder.itemView.findViewById(R.id.hourlyCondTV);

        hourlyTemp.setText(String.valueOf(model.getSingleton().getHourlyData().get(position).getTemp()));
        hourlyCond.setText(model.getSingleton().getHourlyData().get(position).getCondition());
    }
    @Override
    public int getItemCount() { return model.getHourlyData().size(); }
}

