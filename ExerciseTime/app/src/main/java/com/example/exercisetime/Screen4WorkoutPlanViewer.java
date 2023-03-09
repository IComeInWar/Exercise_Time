package com.example.exercisetime;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Screen4WorkoutPlanViewer extends AppCompatActivity {
    public WorkoutPlanSQLite d;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4);
        d =  new WorkoutPlanSQLite(this);
        Button gotoScreen6 = findViewById(R.id.gotoScreen6);
        GridView gridView = findViewById(R.id.Screen4Grid);
        RecyclerView recyclerView = findViewById(R.id.Screen4Recycler);
        String[] projection = {
                WorkoutPlanSQLite.WPLAN_ID,
                WorkoutPlanSQLite.WPLAN_EXERCISES,
                WorkoutPlanSQLite.EXERCISE_ID,
                WorkoutPlanSQLite.EXERCISE_NAME,
                WorkoutPlanSQLite.EQUIPMENT_NAME,
                WorkoutPlanSQLite.MUSCLEGF_NAME
        };

        Cursor cursorWPlan = d.db.query(
                WorkoutPlanSQLite.WORKOUTPLANS_TABLE,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        Cursor cursorEPlan = d.db.query(
                WorkoutPlanSQLite.EXERCISE_TABLE,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        Screen4ViewWorkoutPlansAdapter adapter1 = new Screen4ViewWorkoutPlansAdapter(this, cursorWPlan, 0);
        Screen4ExerciseAdapter adapter2 = new Screen4ExerciseAdapter(this, cursorEPlan);
        gridView.setAdapter(adapter1);
        gridView.setNumColumns(2);
        gridView.setHorizontalSpacing(16);
        gridView.setVerticalSpacing(16);

        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gotoScreen6.setOnClickListener(view -> {
            Intent intent = new Intent(Screen4WorkoutPlanViewer.this, Screen6ExerciseTimer.class);
            startActivity(intent);
        });



    }
}
