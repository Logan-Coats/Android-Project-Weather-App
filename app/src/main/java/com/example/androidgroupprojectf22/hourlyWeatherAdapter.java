package com.example.androidgroupprojectf22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class hourlyWeatherAdapter extends RecyclerView.Adapter<hourlyWeatherAdapter.MyViewHolder> {
    private ArrayList<hourlyModel> weatherEntries;


    public hourlyWeatherAdapter(ArrayList<hourlyModel> weatherEntries) {
        this.weatherEntries = weatherEntries;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(final View view){
            super(view);
        }
    }


    @NonNull
    @Override
    public hourlyWeatherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_cell, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull hourlyWeatherAdapter.MyViewHolder holder, int position) {
        TextView hourlyCell = holder.itemView.findViewById(R.id.hourlyCell);
        hourlyCell.setText(weatherEntries.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return weatherEntries.size();
    }


}
