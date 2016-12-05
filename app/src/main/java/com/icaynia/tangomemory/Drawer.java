package com.icaynia.tangomemory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        adapter.add("Home");
        adapter.add("Word");
        adapter.add("Game");
        adapter.add("Setting");
        adapter.add("Account");
        adapter.add("1");
        adapter.add("2");

        listView.setAdapter(adapter);
    }

    public void viewInitialize() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_drawer, this, false);
        addView(v);
        listView = (ListView) v.findViewById(R.id.view_drawer_listview);
    }



}


