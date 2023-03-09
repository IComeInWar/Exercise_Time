package com.example.exercisetime;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Screen3ViewWorkoutPlans extends AppCompatActivity {
    public WorkoutPlanSQLite d;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);
        RecyclerView recyclerView = findViewById(R.id.Screen3Recycler);
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor cursor = d.getManifestNames(db);

        Screen3LoadWorkoutsAdapter adapter = new Screen3LoadWorkoutsAdapter(this, cursor);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
