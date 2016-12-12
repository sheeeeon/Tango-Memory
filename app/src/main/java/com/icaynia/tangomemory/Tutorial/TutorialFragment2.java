package com.icaynia.tangomemory.Tutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icaynia.tangomemory.R;

/**
 * Created by icaynia on 2016. 12. 8..
 */

public class TutorialFragment2 extends android.support.v4.app.Fragment {
    private View v;

    public TutorialFragment2() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tutorial2, container, false);
        initialize();
        return v;
    }

    public void initialize() {
        viewInitialize();
    }

    public void viewInitialize() {

    }
}
