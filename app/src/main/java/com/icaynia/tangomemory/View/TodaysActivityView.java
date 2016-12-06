package com.icaynia.tangomemory.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icaynia.tangomemory.R;

import java.util.logging.LogManager;

/**
 * Created by icaynia on 2016. 12. 6..
 */

public class TodaysActivityView extends LinearLayout {
    private View v;

    private TextView todayaddcount;
    private TextView solvedcount;

    public TodaysActivityView(Context context) {
        super(context);
        initialize();
    }

    public TodaysActivityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.view_todaysactivityview, this, false);
        addView(v);

        todayaddcount = (TextView) v.findViewById(R.id.view_todaysactivityview_addwordcount);
        solvedcount = (TextView) v.findViewById(R.id.view_todaysactivityview_solvedcount);
    }

    public void setTodayaddcountValue(String str) {
        todayaddcount.setText(str);
    }

    public void setSolvedcountValue(String str) {
        solvedcount.setText(str);
    }

    public View getView() {
        return getRootView();
    }

}
