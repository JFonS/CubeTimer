package com.jfons.cubetimer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimesDbHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 9;

    static final String DATABASE_NAME = "favourites.db";

    public TimesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " +
                TimesContract.TimeEntry.TABLE_NAME + " (" +
                TimesContract.TimeEntry.COLUMN_TIME + " LONG NOT NULL, " +
                TimesContract.TimeEntry.COLUMN_CUBE + " TEXT NOT NULL, " +
                TimesContract.TimeEntry.COLUMN_DATE + " DATE PRIMARY KEY" +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TimesContract.TimeEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
