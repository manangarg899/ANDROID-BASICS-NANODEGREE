package com.example.manan.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

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

        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_HABIT_DESCRIPTION + " TEXT , "
                + HabitContract.HabitEntry.COLUMN_IMPORTANCE + " INTEGER NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_START_TIME + " INTEGER , "
                + HabitContract.HabitEntry.COLUMN_TIME_SPENT + " INTEGER DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
