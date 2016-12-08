package com.icaynia.tangomemory.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icaynia.tangomemory.R;

/**
 * Created by icaynia on 2016. 12. 8..
 */

public class GameActivityView extends LinearLayout {
    private View v;

    private TextView kanjitohiragana;
    private TextView allcount;

    public GameActivityView(Context context) {
        super(context);
        initialize();
    }

    public GameActivityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.view_gameactivityview, this, false);
        addView(v);

        kanjitohiragana = (TextView) v.findViewById(R.id.view_gameactivityview_kanjitohiragana);
        allcount = (TextView) v.findViewById(R.id.view_gameactivityview_allcount);
    }

    public void setAllcountValue(String str) {
        allcount.setText(str);
    }

    public void setKanjitohiraganaValue(String str) {
        kanjitohiragana.setText(str);
    }

    public View getView() {
        return getRootView();
    }
}
