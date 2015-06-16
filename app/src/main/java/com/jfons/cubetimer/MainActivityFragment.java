package com.jfons.cubetimer;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jfons.cubetimer.data.TimesContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ListView listView;
    private TimesListAdapter adapter;
    public static MainActivityFragment fragment;
    public MainActivityFragment()
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

            adapter.add(time);
        }

        return rootView;
    }
}
