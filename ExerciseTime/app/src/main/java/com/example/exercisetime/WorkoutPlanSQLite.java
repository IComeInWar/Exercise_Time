package com.example.exercisetime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WorkoutPlanSQLite extends SQLiteOpenHelper {
    public SQLiteDatabase db;
    public static final String WPLAN_DATABASE = "WorkoutPlans.db";
    private static final int DATABASE_VERSION = 2;

    public static final String MANIFEST_TABLE = "Manifest_table";

    public static final String MANIFEST_ID = "Manifest_id";
    public static final String MANIFEST_NAME = "Manifest_name";
    public static final String MANIFEST_DURATION = "Manifest_duration";

    public static final String EXERCISE_TABLE = "ExerciseData";

    public static final String EXERCISE_ID = "Exercise_id";
    public static final String EXERCISE_NAME = "Exercise_name";
    public static final String EQUIPMENT_NAME = "Equipment_name";
    public static final String MUSCLEGF_NAME = "Musclegfocus";
    String createEPlanTableQuery = "CREATE TABLE IF NOT EXISTS " + EXERCISE_TABLE + " ("
            + EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EXERCISE_NAME + " TEXT,"
            + EQUIPMENT_NAME + " TEXT,"
            + MUSCLEGF_NAME + " TEXT" +
            ")";


    public static final String WORKOUTPLANS_TABLE = "WorkoutPlan";


    public static final String WPLAN_ID = "wplan_id";
    public static final String WPLAN_EXERCISES = "wplan_exercises";

    public static String CREATE_WORKOUTPLANS_TABLE(int manifestId, int workout_no) {
        StringBuilder q = new StringBuilder("CREATE TABLE " + WORKOUTPLANS_TABLE + "_" + manifestId + "("
                + WPLAN_ID + " INTEGER PRIMARY KEY, ");
        for (int i = 1; i <= workout_no; i++) {
            q.append(WPLAN_EXERCISES).append(i).append(" TEXT, ");
            q.append(EXERCISE_NAME).append(i).append(" TEXT");
            if (i < workout_no) {
                q.append(", ");
            }
        }
        q.append(")");
        return q.toString();
    }

    private static final String DROP_WORKOUTPLANS_TABLE = "DROP TABLE IF EXISTS " + WORKOUTPLANS_TABLE;

    public WorkoutPlanSQLite(Context context) {
        super(context, WPLAN_DATABASE, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MANIFEST_TABLE = "CREATE TABLE "
                + MANIFEST_TABLE + "("
                + MANIFEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MANIFEST_NAME + " TEXT NOT NULL," +
                MANIFEST_DURATION + " INTEGER NOT NULL " +
                ")";
        db.execSQL(CREATE_MANIFEST_TABLE);
            db.execSQL(createEPlanTableQuery);

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(DROP_WORKOUTPLANS_TABLE);
            onCreate(db);
        }
    }

    public boolean insertDataExercise_table(String exercise_name, String equipment_name,
                                            String musclegfocus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_NAME, exercise_name);
        contentValues.put(EQUIPMENT_NAME, equipment_name);
        contentValues.put(MUSCLEGF_NAME, musclegfocus);
        long result = db.insert(EXERCISE_TABLE, null, contentValues);
        return result != -1L;
    }
    public Cursor getManifestNames(SQLiteDatabase db) {
        String selectQuery = "SELECT " + MANIFEST_NAME + " FROM " + MANIFEST_TABLE;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public Cursor getExerciseNames() {
        String selectQuery = "SELECT " + EXERCISE_NAME + " FROM " + EXERCISE_TABLE;
        return db.rawQuery(selectQuery, null);
    }


}
