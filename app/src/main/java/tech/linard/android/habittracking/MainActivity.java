package tech.linard.android.habittracking;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import tech.linard.android.habittracking.data.HabitDBHelper;
import tech.linard.android.habittracking.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    HabitDBHelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertDummyData();
        Cursor cursor = readDummyData();
        displayDummyData(cursor);
    }

    private void displayDummyData(Cursor cursor) {
        int indexAction = cursor.getColumnIndex(HabitEntry.COLUMN_ACTION);
        int indexDuration = cursor.getColumnIndex(HabitEntry.COLUMN_DURATION);
        int indexDate = cursor.getColumnIndex(HabitEntry.COLUMN_DATE);

        TextView txtCount = (TextView) findViewById(R.id.txt_count);
        txtCount.setText(Integer.toString(cursor.getCount()));
        while (cursor.moveToNext()) {
            String action = cursor.getString(indexAction);
            int duration  = cursor.getInt(indexDuration);
            String date = cursor.getString(indexDate);

            txtCount.append(
                    "\n"
                            + action + " - "
                            + Integer.toString(duration) + " - "
                            + date
            );
        }
    }

    private Cursor readDummyData() {
        mDBHelper = new HabitDBHelper(this);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        // Prepare the select statement;
        String[] projection = {
                HabitEntry.COLUMN_ACTION,
                HabitEntry.COLUMN_DURATION,
                HabitEntry.COLUMN_DATE
        };

        return  db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    private void insertDummyData() {
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_ACTION, "Android Programming");
        values.put(HabitEntry.COLUMN_DURATION, 240 ); // minutes
        values.put(HabitEntry.COLUMN_DATE, "03.12.2016");
        mDBHelper = new HabitDBHelper(this);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long result = db.insert(HabitEntry.TABLE_NAME, null, values);
        if (result == -1) {
            Toast.makeText(this, "INSERT UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "INSERT SUCCESSFUL with ID of " + result, Toast.LENGTH_SHORT).show();
        }
    }

}
