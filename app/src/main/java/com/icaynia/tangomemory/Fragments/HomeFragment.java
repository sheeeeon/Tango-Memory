package com.icaynia.tangomemory.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icaynia.tangomemory.Data.wordManager;
import com.icaynia.tangomemory.R;

/**
 * Created by icaynia on 2016. 12. 5..
 */

public class HomeFragment extends android.support.v4.app.Fragment  {
    private View fragmentView;
    private wordManager mWordManager;

    public HomeFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        initialize();
        return fragmentView;
    }

    private void initialize() {
        mWordManager = new wordManager(getContext());


    }

}
