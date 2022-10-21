package com.example.androidgroupprojectf22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class activity_history extends AppCompatActivity {

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            RecyclerView historyRV = findViewById(R.id.historyRV);
            View view = historyRV.findChildViewUnder(e.getX(), e.getY());
            if(view != null) {
                RecyclerView.ViewHolder holder = historyRV.getChildViewHolder(view);
                if (holder instanceof HistoryAdapter.HistoryViewHolder) {
                    int position = holder.getAdapterPosition();
                    // Single tap
                    Log.d("click", "clicked on item " + position);

                    historyServer.notifyDataSetChanged();
                    return true;
                }
            }
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    private HistoryModel model = HistoryModel.getModel();
    HistoryAdapter historyServer = new HistoryAdapter(HistoryModel.getModel());
}