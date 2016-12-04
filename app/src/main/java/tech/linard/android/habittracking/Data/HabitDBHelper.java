package tech.linard.android.habittracking.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import tech.linard.android.habittracking.Data.HabitContract.HabitEntry;
/**
 * Created by lucas on 04/12/16.
 */

public class HabitDBHelper extends SQLiteOpenHelper {
    // DB Management
    public static final String LOG_TAG = HabitDBHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "habittracker.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public HabitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String PRIMARY_KEY = " PRIMARY KEY ";
        final String AUTOINCREMENT = " AUTOINCREMENT ";
        final String NOT_NULL  = " NOT NULL ";
        final String TEXT = " TEXT ";
        final String INTEGER = " INTEGER ";
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME
                + " ( "+ HabitEntry.COLUMN_ID + INTEGER + PRIMARY_KEY + AUTOINCREMENT
                + ", " + HabitEntry.COLUMN_ACTION + TEXT + NOT_NULL
                + ", " + HabitEntry.COLUMN_DURATION + INTEGER
                + ", " + HabitEntry.COLUMN_DATE + TEXT + NOT_NULL
                + ");"
                ;
        sqLiteDatabase.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
