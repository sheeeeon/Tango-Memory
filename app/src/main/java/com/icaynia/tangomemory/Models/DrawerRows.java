package com.icaynia.tangomemory.Models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by icaynia on 2016. 12. 6..
 */

public class DrawerRows {
    public Drawable icon;
    public String text;

    public DrawerRows(Drawable icon, String str) {
        this.icon = icon;
        this.text = str;
    }

}
