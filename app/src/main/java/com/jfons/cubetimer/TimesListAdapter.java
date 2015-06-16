package com.jfons.cubetimer;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
            li = LayoutInflater.from(MainActivityFragment.fragment.getActivity());
            v = li.inflate(R.layout.list_item_times, null);
        }

        final String time = times.get(position);
        if (time != null)
        {
            TextView timeText = (TextView) v.findViewById(R.id.list_item_time_textview);


            timeText.setText(time);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Log.d("JFonS","CLICKITTY CLICK");
                }
            });
        }
        return v;
    }

}