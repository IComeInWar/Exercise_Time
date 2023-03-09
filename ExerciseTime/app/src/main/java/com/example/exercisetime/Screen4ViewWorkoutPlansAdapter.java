package com.example.exercisetime;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Screen4ViewWorkoutPlansAdapter extends CursorAdapter {

    public Screen4ViewWorkoutPlansAdapter(Screen4WorkoutPlanViewer context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate your layout here
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.screen4, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Bind data to your views here
        String workoutPlanName = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutPlanSQLite.MANIFEST_NAME));
        TextView textView = view.findViewById(R.id.nameTextView);
        textView.setText(workoutPlanName);
    }
}


