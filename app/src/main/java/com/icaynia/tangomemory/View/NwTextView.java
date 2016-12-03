package com.icaynia.tangomemory.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by icaynia on 2016. 12. 3..
 */

public class NwTextView extends TextView {
    public NwTextView(Context context) {
        super(context);
        setType(context);
    }

    public NwTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setType(context);
    }

    public NwTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setType(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NwTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setType(context);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "norwester.otf"));
    }
}
