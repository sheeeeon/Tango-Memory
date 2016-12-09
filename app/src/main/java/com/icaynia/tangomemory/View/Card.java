package com.icaynia.tangomemory.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.icaynia.tangomemory.R;

/**
 * Created by icaynia on 2016. 12. 5..
 */

public class Card extends LinearLayout
{
    private LinearLayout box;
    private TextView title;
    private View mainContent;
    private LinearLayout topLL;

    private View v;

    public int[][] theme = {
            {1, 3}
    };

    public Card(Context context) {
        super(context);
        initialize();
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {
        viewInitialize();
    }

    public void viewInitialize() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.view_card, this, false);
        addView(v);

        box = (LinearLayout) v.findViewById(R.id.view_card_box);
        title = (TextView) v.findViewById(R.id.view_card_title);
        topLL = (LinearLayout) v.findViewById(R.id.view_card_content);
    }

    public void setTheme(int theme) {
        switch (theme) {
            case 0: // default 1
                box.setBackgroundColor(0x104b03);
                break;
            case 1: // default 2
                box.setBackgroundColor(0xC6C6C6);
                break;
            case 2: // warning 1
                box.setBackgroundColor(0x784C00);
                break;
            case 3: // warning 2
                box.setBackgroundColor(0x791800);
                break;

        }
    }

    public void setTitle(String titleStr) {
        title.setText(titleStr);
    }

    public void setContent(View content) {
        mainContent = content;
        topLL.addView(content);
    }

    public View getContentView() {
        return mainContent;
    }

}
