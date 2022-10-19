package com.example.androidgroupprojectf22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeeklyAdapter extends RecyclerView.Adapter<WeeklyAdapter.WeeklyViewHolder> {
    public static class WeeklyViewHolder extends RecyclerView.ViewHolder{
        public WeeklyViewHolder(View v){
            super(v);
        }
    }
    private WeeklyModel model = WeeklyModel.getModel();
    public WeeklyAdapter(){super();}

    @NonNull
    @Override
    public WeeklyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weekly_cell,parent,false);
        WeeklyViewHolder vh = new WeeklyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyViewHolder holder, int position){
        TextView weeklyTempLow = holder.itemView.findViewById(R.id.weeklyTempLow);
        TextView weeklyTempHigh = holder.itemView.findViewById(R.id.weeklyTempHigh);
        TextView weeklyCondition = holder.itemView.findViewById(R.id.weeklyCondition);

        weeklyCondition.setText(model.getWeeklyData().get(position).getCondition());
        weeklyTempLow.setText(String.valueOf(model.getWeeklyData().get(position).getLow()));
        weeklyTempHigh.setText(String.valueOf(model.getWeeklyData().get(position).getHigh()));
    }
    @Override
    public int getItemCount() { return model.getWeeklyData().size(); }
}
