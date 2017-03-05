package com.example.manan.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Manan on 03-03-2017.
 */

public class HabitContract {

    public static final class HabitEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Habit";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT = "HabitName";
        public static final String COLUMN_HABIT_DESCRIPTION = "Description";
        public static final String COLUMN_IMPORTANCE = "Importance";
        public static final String COLUMN_TIME_SPENT = "TimeSpent";
        public static final String COLUMN_START_TIME = "StartTime";

        public static final int IMPORTANCE_YES = 1;
        public static final int IMPORTANCE_NO = 0;
    }
}
