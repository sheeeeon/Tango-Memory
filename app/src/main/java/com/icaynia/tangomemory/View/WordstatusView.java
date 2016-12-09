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

public class WordstatusView extends LinearLayout {
    private View v;

    private TextView st1;
    private TextView st2;
    private TextView st3;
    private TextView st4;
    private TextView st5;

    private LinearLayout rankingBox;

    private TextView titleTv1;


    public WordstatusView(Context context) {
        super(context);
        initialize();
    }

    public WordstatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.view_wordstatusview, this, false);
        addView(v);

        st1 = (TextView) v.findViewById(R.id.view_wordstatusview_1st);
        st2 = (TextView) v.findViewById(R.id.view_wordstatusview_2st);
        st3 = (TextView) v.findViewById(R.id.view_wordstatusview_3st);
        st4 = (TextView) v.findViewById(R.id.view_wordstatusview_4st);
        st5 = (TextView) v.findViewById(R.id.view_wordstatusview_5st);

        rankingBox = (LinearLayout) v.findViewById(R.id.view_wordstatusview_rankingbox);

        titleTv1 = (TextView) v.findViewById(R.id.view_wordstatusview_title1);
    }

    public void setMostwrongWord(int st, String str) {
        TextView stt = null;
        switch (st) {
            case 1: stt = st1; break;
            case 2: stt = st2; break;
            case 3: stt = st3; break;
            case 4: stt = st4; break;
            case 5: stt = st5; break;
        }
        stt.setText(str);
    }

    public View getView() {
        return getRootView();
    }

    public void setRankingVisible(int visible) {
        rankingBox.setVisibility(visible);
    }

    public void setTitleText(String str) {
        titleTv1.setText(str);
    }

}
