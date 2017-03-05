package com.example.manan.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.manan.habittracker.data.HabitContract.HabitEntry;
import com.example.manan.habittracker.data.HabitDbHelper;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
        insertHabit();
        DisplayInfo();

    }

    @Override
    protected void onStart() {
        super.onStart();
        DisplayInfo();
    }

    public void insertHabit()
    {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT,"Running");
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION,"Keeps us fit");
        values.put(HabitEntry.COLUMN_IMPORTANCE,HabitEntry.IMPORTANCE_YES);
        values.put(HabitEntry.COLUMN_START_TIME,18);
        values.put(HabitEntry.COLUMN_TIME_SPENT,1);
        long newRowId = database.insert(HabitEntry.TABLE_NAME,null,values);
    }

    public void DisplayInfo()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String [] projection = {HabitEntry._ID,HabitEntry.COLUMN_HABIT,HabitEntry.COLUMN_HABIT_DESCRIPTION,
                HabitEntry.COLUMN_IMPORTANCE,HabitEntry.COLUMN_TIME_SPENT,HabitEntry.COLUMN_START_TIME};

        Cursor cursor = db.query(HabitEntry.TABLE_NAME,projection,null,null,null,null,null);

        TextView displayView = (TextView) findViewById(R.id.display_info);

        try {
            displayView.setText("The pets table contains " + cursor.getCount() + " Habit.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT + " - " +
                    HabitEntry.COLUMN_HABIT_DESCRIPTION + " - " +
                    HabitEntry.COLUMN_IMPORTANCE + " - " +
                    HabitEntry.COLUMN_START_TIME + " - " +
                    HabitEntry.COLUMN_TIME_SPENT + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int habitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT);
            int habit_desc_ColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DESCRIPTION);
            int ImportanceColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_IMPORTANCE);
            int startTimeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_START_TIME);
            int timeSpentColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_TIME_SPENT);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentHabit = cursor.getString(habitColumnIndex);
                String currentHabitDesc = cursor.getString(habit_desc_ColumnIndex);
                int currentStartTime = cursor.getInt(startTimeColumnIndex);
                int currentTimeSpent = cursor.getInt(timeSpentColumnIndex);
                int currentImportance = cursor.getInt(ImportanceColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentHabit + " - " +
                        currentHabitDesc + " - " +
                        currentImportance + " - " +
                        currentStartTime + " - " +
                        currentTimeSpent));
            }
        }
        finally {
            cursor.close();
        }

    }
}
