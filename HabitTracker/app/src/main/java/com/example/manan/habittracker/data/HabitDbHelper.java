package com.example.manan.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.manan.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Manan on 03-03-2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habitTracker.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE "
                + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DESCRIPTION + " TEXT , "
                + HabitEntry.COLUMN_IMPORTANCE + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_START_TIME + " INTEGER , "
                + HabitEntry.COLUMN_TIME_SPENT + " INTEGER DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertHabit() {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT, "Running");
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION, "Keeps us fit");
        values.put(HabitEntry.COLUMN_IMPORTANCE, HabitEntry.IMPORTANCE_YES);
        values.put(HabitEntry.COLUMN_START_TIME, 18);
        values.put(HabitEntry.COLUMN_TIME_SPENT, 1);
        database.insert(HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor readHabit() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT,
                HabitEntry.COLUMN_HABIT_DESCRIPTION,
                HabitEntry.COLUMN_IMPORTANCE,
                HabitEntry.COLUMN_TIME_SPENT,
                HabitEntry.COLUMN_START_TIME
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);

        return cursor;
    }
}
