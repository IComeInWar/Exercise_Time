package com.example.exercisetime;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Screen2CreateWorkoutPlan extends AppCompatActivity {
    private WorkoutPlanSQLite d;
    Button BtnCreateWorkout;
    Spinner SpinnerDuration;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        BtnCreateWorkout = findViewById(R.id.button_gotoscreen5);
        SpinnerDuration = findViewById(R.id.workout_duration);
        d = new WorkoutPlanSQLite(this);
        SQLiteDatabase db = d.getReadableDatabase();
        Cursor cursor = d.getManifestNames(db);

        findViewById(R.id.button_gotoscreen5).setOnClickListener(view -> {
            final String name = ((EditText) findViewById(R.id.edit_text_EPlan)).getText().toString();
            if (!CommonDialogs.alphanumericDialogue(name, "workout plan name", Screen2CreateWorkoutPlan.this)) {
                return;
            }
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(0).equals(name)) {
                        CommonDialogs.errorDialogue("This workout plan name is already in use! " +
                                "Please try a different name.", Screen2CreateWorkoutPlan.this);
                        return;
                    }
                } while (cursor.moveToNext());
            }
            String duration = SpinnerDuration.getSelectedItem().toString();
            int duration_N = Integer.parseInt(duration);
            CommonDialogs.confirmDialogue("workout_plan Name: \"" + name + "\"\nDuration of workout: " + duration_N,
                    (dialog, which) -> {
                        Intent intent = new Intent(Screen2CreateWorkoutPlan.this,
                                Screen4WorkoutPlanViewer.class);
                        intent.putExtra(WorkoutPlanSQLite.MANIFEST_ID, insertManifest(name, duration_N));
                        startActivity(intent);
                        finish();
                    }, Screen2CreateWorkoutPlan.this);
        });
    }
    public int getNextManifestId() {
        SQLiteDatabase db = d.getReadableDatabase();
        String query = "SELECT MAX(" + WorkoutPlanSQLite.MANIFEST_ID + ") FROM " + WorkoutPlanSQLite.MANIFEST_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int manifestId = 1;
        if (cursor.moveToFirst()) {
            manifestId = cursor.getInt(0) + 1;
        }
        cursor.close();
        return manifestId;
    }

    public int insertManifest(String name, Integer duration_N){
        ContentValues values = new ContentValues();
        int manifestID = getNextManifestId();
        values.put(d.MANIFEST_ID, manifestID);
        values.put(d.MANIFEST_NAME, name);
        values.put(d.MANIFEST_DURATION, duration_N);
        d.db.insert(d.MANIFEST_TABLE, null, values);
        d.db.execSQL(d.createEPlanTableQuery);
        d.db.execSQL(d.CREATE_WORKOUTPLANS_TABLE(manifestID, duration_N));

        int sets = 9;
        int durationPerSet = 10;
        int numColumns = (duration_N % 5 == 0) ? duration_N / 5 : (duration_N / 5) + 1;

        for (int i = 0; i < sets ; i++) {
            ContentValues values2 = new ContentValues();
            values2.put(String.valueOf(manifestID), i);
            for (int j = 1; j <= numColumns; j++) {
                values2.put(String.valueOf(durationPerSet + j), -1);
            }
            String tableName = d.WORKOUTPLANS_TABLE+ "_" + manifestID;
            d.db.insert(tableName, null, values2);
            d.db.execSQL(d.CREATE_WORKOUTPLANS_TABLE(manifestID, numColumns) + "_" + manifestID);
        }
        return manifestID;
    }


}