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

public class YourTangoActivityView extends LinearLayout {
    private View v;

    private TextView wordquantity;

    public YourTangoActivityView(Context context) {
        super(context);
        initialize();
    }

    public YourTangoActivityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.view_yourtangoactivityview, this, false);
        addView(v);

        wordquantity = (TextView) v.findViewById(R.id.view_yourtangoactivityview_wordquantity);
    }

    public void setWordquantityValue(String str) {
        wordquantity.setText(str);
    }

    public View getView() {
        return getRootView();
    }

}
