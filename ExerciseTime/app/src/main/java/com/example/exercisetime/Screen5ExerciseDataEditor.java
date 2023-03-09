package com.example.exercisetime;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Screen5ExerciseDataEditor extends AppCompatActivity {
    EditText ExerciseN;
    EditText EquipmentN;
    EditText MuscleG;
    WorkoutPlanSQLite ExerciseDB;
    Button Create_E;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen5);
        ExerciseDB = new WorkoutPlanSQLite(this);

        ExerciseN = findViewById(R.id.edit_text_exercise_name);
        EquipmentN = findViewById(R.id.edit_text_equipment_needed);
        MuscleG = findViewById(R.id.edit_text_muscle_group);
        Create_E = findViewById(R.id.button_create_E);

        Create_E.setOnClickListener(view -> {
            String Exercise = ExerciseN.getText().toString();
            String Equipment = EquipmentN.getText().toString();
            String MuscleGroup = MuscleG.getText().toString();

            ExerciseDB.insertDataExercise_table(Exercise, Equipment, MuscleGroup);
            Cursor exerciseNames = ExerciseDB.getExerciseNames();
            if (!CommonDialogs.alphanumericDialogue(Exercise, "exercise name", Screen5ExerciseDataEditor.this)) {
                return;
            }
            if (!CommonDialogs.alphanumericDialogue(Equipment, "name of equipment", Screen5ExerciseDataEditor.this)) {
                return;
            }
            CommonDialogs.alphanumericDialogue(MuscleGroup, "muscle group name", Screen5ExerciseDataEditor.this);
        });
    }
}
