package com.jfons.cubetimer;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jfons.cubetimer.data.TimesContract;

import java.security.Timestamp;
import java.text.DateFormat;
import java.util.Date;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment {
    private ListView listView;
    private TimesListAdapter adapter;
    public static ListFragment fragment;
    public ListFragment()
    {
        adapter = new TimesListAdapter();
        fragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        adapter.clear();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview_times);
        listView.setAdapter(adapter);

        Cursor c = getActivity().getContentResolver().query(
                TimesContract.TimeEntry.CONTENT_URI, null, null, null, null);
        c.moveToFirst();

        while(c.moveToNext())
        {
            String time = c.getString(0);
            time += "--";
            time += c.getString(2);
            adapter.add(time);
        }

        return rootView;
    }
}
