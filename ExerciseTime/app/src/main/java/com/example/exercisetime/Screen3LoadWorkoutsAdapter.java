package com.example.exercisetime;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Screen3LoadWorkoutsAdapter extends RecyclerView.Adapter<Screen3LoadWorkoutsAdapter.WorkoutPlanViewHolder> {
    private final Context context;
    private final Cursor cursor;

    public Screen3LoadWorkoutsAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public WorkoutPlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screen3fragment, parent, false);
        return new WorkoutPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutPlanViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String workoutPlanName = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutPlanSQLite.MANIFEST_NAME));
        holder.workoutNameTextView.setText(workoutPlanName);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Screen4WorkoutPlanViewer.class);
            intent.putExtra("workoutPlanName", workoutPlanName);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class WorkoutPlanViewHolder extends RecyclerView.ViewHolder {
        public TextView workoutNameTextView;

        public WorkoutPlanViewHolder(View itemView) {
            super(itemView);
            workoutNameTextView = itemView.findViewById(R.id.workout_name);
        }
    }
}

