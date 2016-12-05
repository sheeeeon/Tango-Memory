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
    private wordManager mWordManager;

    public HomeFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initialize();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void initialize() {
        mWordManager = new wordManager(getContext());
    }

}
