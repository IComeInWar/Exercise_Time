package com.example.exercisetime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Screen1Homepage extends AppCompatActivity {
Button button1;
Button button2;
Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        button1 = findViewById(R.id.button_CrtWrkPln);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(Screen1Homepage.this, Screen2CreateWorkoutPlan.class);
            startActivity(intent);

        });
        button2 = findViewById(R.id.button_LdWrkPln);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(Screen1Homepage.this, Screen3ViewWorkoutPlans.class);
            startActivity(intent);
        });
        button3 = findViewById(R.id.button_Create_Exercises);
        button3.setOnClickListener(view -> {
            Intent intent = new Intent(Screen1Homepage.this, Screen5ExerciseDataEditor.class);
            startActivity(intent);
        });
    }
}
