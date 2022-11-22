package com.example.androidgroupprojectf22;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        hourlyViewHolder vh = new hourlyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull hourlyAdapter.hourlyViewHolder holder, int position){
        TextView currentTime = holder.itemView.findViewById(R.id.timeTV);
        TextView hourlyTemp = holder.itemView.findViewById(R.id.currHourlyTempTV);
        ImageView currCondition = holder.itemView.findViewById(R.id.currConditionIMG);

        currentTime.setText(model.getSingleton().getHourlyData().get(position).getTime());
        hourlyTemp.setText(String.valueOf(model.getSingleton().getHourlyData().get(position).getTemp()));
        Glide.with(holder.itemView.getContext()).load("https://" + model.getSingleton()
                .getHourlyData().get(position).getCondition()).into(currCondition);

    }
    @Override
    public int getItemCount() { return model.getHourlyData().size(); }
}

