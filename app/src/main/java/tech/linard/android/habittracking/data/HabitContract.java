package tech.linard.android.habittracking.data;

import android.provider.BaseColumns;

/**
 * Created by lucas on 04/12/16.
 */

public final class HabitContract {
    private HabitContract() {
        throw new AssertionError("No HabitContract instances for you!");
    }
    public static class HabitEntry implements BaseColumns {
        // Table defined here;
        public final static String TABLE_NAME = "habits";
        public final static String COLUMN_ID = BaseColumns._ID;
        public final static String COLUMN_ACTION = "action";
        public final static String COLUMN_DURATION = "duration";
        public final static String COLUMN_DATE = "date";
    }
}
