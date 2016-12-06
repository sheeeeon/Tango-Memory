package com.icaynia.tangomemory.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icaynia.tangomemory.Data.logManager;
import com.icaynia.tangomemory.Data.wordManager;
import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.View.Card;
import com.icaynia.tangomemory.View.TodaysActivityView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.LogManager;

import static com.icaynia.tangomemory.R.id.card1;

/**
 * Created by icaynia on 2016. 12. 5..
 */

public class HomeFragment extends android.support.v4.app.Fragment  {
    private View fragmentView;
    private wordManager mWordManager;
    private logManager mLogManager;

    Date date = new Date();
    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd" , Locale.KOREA);

    private Card card1;

    public HomeFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        initialize();
        return fragmentView;
    }

    private void initialize() {
        viewInitialize();
        mWordManager = new wordManager(getContext());
        mLogManager = new logManager(getContext());
        card1();
    }

    private void viewInitialize() {
        card1 = (Card) fragmentView.findViewById(R.id.card1);

    }

    private void card1() {
        card1.setTitle("Today's activity");
        TodaysActivityView tav = new TodaysActivityView(getContext());
        tav.setValue(
                mLogManager.getCount(transFormat.format(date))+"");

        card1.setContent(tav.getView());
    }



}
