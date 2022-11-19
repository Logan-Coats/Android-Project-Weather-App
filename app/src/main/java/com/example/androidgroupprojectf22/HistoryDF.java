package com.example.androidgroupprojectf22;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class HistoryDF extends DialogFragment {
    public interface HistorySelectionCallbacks {
        void positiveAction();
        void negativeAction();
    }

    HistorySelectionCallbacks myActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (HistorySelectionCallbacks) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Remove selection?")
                .setMessage("Removes this item from your saved locations. This location can be added back by searching for it again.")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myActivity.negativeAction();
                    }
                })
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myActivity.positiveAction();
                    }
                });
        return builder.create();
    }
}
