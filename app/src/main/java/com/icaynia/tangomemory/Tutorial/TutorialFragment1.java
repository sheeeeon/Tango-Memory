package com.icaynia.tangomemory.Tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.View.Card;
import com.icaynia.tangomemory.wordtest.WordToHiragana;

/**
 * Created by icaynia on 2016. 12. 8..
 */

public class TutorialFragment1 extends android.support.v4.app.Fragment {
    private View v;

    public TutorialFragment1() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tutorial1, container, false);
        initialize();
        return v;
    }

    public void initialize() {
        viewInitialize();
    }

    public void viewInitialize() {

    }
}
