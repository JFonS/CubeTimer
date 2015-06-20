package com.jfons.cubetimer;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jfons.cubetimer.data.TimesContract;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimesListAdapter extends BaseAdapter
{
    ArrayList<String> times;
    public TimesListAdapter()
    {
        times = new ArrayList<String>();
    }

    @Override
    public int getCount() { return times.size();  }

    @Override
    public Object getItem(int position) { return times.get(position);  }

    @Override
    public long getItemId(int position) { return position; }

    public void add(String vi) { times.add(vi); }
    public void clear() { times.clear(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater li;
            li = LayoutInflater.from(ListFragment.fragment.getActivity());
            v = li.inflate(R.layout.list_item_times, null);
        }

        final String time = times.get(position);
        if (time != null)
        {
            String [] data = time.split("--");
            TextView timeText = (TextView) v.findViewById(R.id.timeText);
            timeText.setText(data[0]);
            TextView dateText = (TextView) v.findViewById(R.id.dateText);
            dateText.setText(data[1]);

        }
        return v;
    }

}