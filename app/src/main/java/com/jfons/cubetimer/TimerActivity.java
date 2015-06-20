package com.jfons.cubetimer;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jfons.cubetimer.data.TimesContract;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class TimerActivity extends ActionBarActivity {

    private long startTime;
    private long elapsedTime;
    private Handler mHandler = new Handler();
    static  int REFRESH_RATE = 10;
    private boolean running;
    private String lastTimestamp;
    private TextView timerText;

    private Runnable startTimer = new Runnable() {
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this, REFRESH_RATE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        View timerLayout = findViewById(R.id.timer_layout);
        Button discardButton = (Button) findViewById(R.id.discardBtn);
        timerText = (TextView) findViewById(R.id.timeText);

        timerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                mHandler.removeCallbacks(startTimer);
                mHandler.postDelayed(startTimer, 0);
                running = true;
            }
        });

        timerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!running) {
                        timerText.setText("0.00");
                        return false;
                    } else {
                        running = false;
                        mHandler.removeCallbacks(startTimer);
                        saveTime(elapsedTime);
                        return true;
                    }
                }
                return false;
            }
        });

        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TimerActivity.this, ListActivity.class);
                startActivity(intent);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
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

    private void updateTimer(float time) {
        long secs, mins;
        secs = (long) (time / 1000);
        mins = (long) ((time / 1000) / 60);
        secs = secs % 60;

        String seconds = String.valueOf(secs);
        if (secs == 0) {
            seconds = "00";
        }
        if (secs < 10 && secs > 0) {
            seconds = "0" + seconds;
        } /* Convert the minutes to String and format the String */

        mins = mins % 60;
        String minutes = String.valueOf(mins);

        String milliseconds = String.valueOf((long) (time % 1000));

        if (milliseconds.length() == 1) {
            milliseconds = "00";
        }
        if (milliseconds.length() == 2) {
            milliseconds = "0" + milliseconds;
        }
        milliseconds = milliseconds.substring(0, 2);

       timerText.setText((mins == 0 ? "" : (minutes + ":")) + seconds + "." + milliseconds);
    }

    private void saveTime(long time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        lastTimestamp =  dateFormat.format(date);
        ContentValues cv = new ContentValues();
        cv.put(TimesContract.TimeEntry.COLUMN_TIME, (String) timerText.getText());
        cv.put(TimesContract.TimeEntry.COLUMN_DATE, lastTimestamp);
        cv.put(TimesContract.TimeEntry.COLUMN_CUBE, "3x3");

        getContentResolver().insert(TimesContract.TimeEntry.CONTENT_URI, cv);
    }
}
