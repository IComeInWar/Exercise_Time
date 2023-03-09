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

public class Screen4ExerciseAdapter extends RecyclerView.Adapter<Screen4ExerciseAdapter.ExerciseDataViewHolder> {
    private final Context context;
    private final Cursor cursor;

    public Screen4ExerciseAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public Screen4ExerciseAdapter.ExerciseDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screen4fragment, parent, false);
        return new Screen4ExerciseAdapter.ExerciseDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Screen4ExerciseAdapter.ExerciseDataViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String exerciseName = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutPlanSQLite.EXERCISE_NAME));
        holder.exerciseNameTextView.setText(exerciseName);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Screen4WorkoutPlanViewer.class);
            intent.putExtra("exerciseName", exerciseName);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class ExerciseDataViewHolder extends RecyclerView.ViewHolder {
        public TextView exerciseNameTextView;

        public ExerciseDataViewHolder(View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.nameTextView);
        }

    }
}

