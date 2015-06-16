package com.jfons.cubetimer;

import android.content.ContentValues;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jfons.cubetimer.data.TimesContract;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random r = new Random();

        for (int i = 0; i < 20; ++i) {
            ContentValues cv = new ContentValues();
            cv.put(TimesContract.TimeEntry.COLUMN_TIME, r.nextFloat());
            cv.put(TimesContract.TimeEntry.COLUMN_DATE, new Timestamp(new Date().getTime()).toString());
            cv.put(TimesContract.TimeEntry.COLUMN_CUBE, "3x3");
            Log.d("JFonS", cv.get(TimesContract.TimeEntry.COLUMN_TIME) + ", " + cv.get(TimesContract.TimeEntry.COLUMN_DATE));
                    getContentResolver().insert(TimesContract.TimeEntry.CONTENT_URI, cv);
        }


        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
