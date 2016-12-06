package com.icaynia.tangomemory.Data;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icaynia.tangomemory.Models.DrawerRows;
import com.icaynia.tangomemory.Models.word;
import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.infActivity;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 12. 6..
 */

public class DrawerListAdapter extends BaseAdapter {
    private ArrayList<DrawerRows> m_List;

    public DrawerListAdapter() {
        m_List = new ArrayList<DrawerRows>();
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        if ( convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_drawer_rows, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.view_drawer_rows_iconView);
        iconView.setImageDrawable(m_List.get(position).icon);

        TextView textView = (TextView) convertView.findViewById(R.id.view_drawer_rows_textView);
        textView.setText(m_List.get(position).text);

        return convertView;
    }

    public void add(DrawerRows data) {
        m_List.add(data);
    }

    public void remove(int position) {
        m_List.remove(position);
    }
}
