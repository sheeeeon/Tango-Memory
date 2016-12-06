package com.icaynia.tangomemory.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.View.Card;
import com.icaynia.tangomemory.wordtest.WordToHiragana;

/**
 * Created by icaynia on 2016. 12. 5..
 */

public class GameFragment extends android.support.v4.app.Fragment {
    private Card menu1;
    private View v;

    public GameFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_game, container, false);
        initialize();
        return v;
    }

    public void initialize() {
        viewInitialize();
    }

    public void viewInitialize() {
        menu1 = (Card) v.findViewById(R.id.kanjiToHiraganaMenu);
        menu1.setTitle("Kanji to Haragana");
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WordToHiragana.class);
                startActivity(intent);
            }
        });
    }
}

