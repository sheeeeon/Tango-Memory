package com.icaynia.tangomemory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.icaynia.tangomemory.Data.DrawerListAdapter;
import com.icaynia.tangomemory.Models.DrawerRows;

/**
 * Created by icaynia on 2016. 12. 5..
 */

public class Drawer extends LinearLayout {
    public ListView listView;
    public LinearLayout profileView;


    public Drawer(Context context) {
        super(context);
        initialize();
    }

    public Drawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {
        viewInitialize();
        DrawerListAdapter adapter = new DrawerListAdapter();
        adapter.add(new DrawerRows(getResources().getDrawable(R.drawable.ic_home), "Home"));
        adapter.add(new DrawerRows(getResources().getDrawable(R.drawable.ic_list), "Word"));
        adapter.add(new DrawerRows(getResources().getDrawable(R.drawable.ic_translate), "Game"));
        listView.setAdapter(adapter);

    }

    public void viewInitialize() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_drawer, this, false);
        addView(v);
        listView = (ListView) v.findViewById(R.id.view_drawer_listview);
    }



}


