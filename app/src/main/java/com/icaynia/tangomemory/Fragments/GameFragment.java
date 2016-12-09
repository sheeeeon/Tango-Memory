package com.icaynia.tangomemory.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.View.Card;
import com.icaynia.tangomemory.wordtest.WordToHiragana;

/**
 * Created by icaynia on 2016. 12. 5..
 */

public class GameFragment extends android.support.v4.app.Fragment {
    private Card menu1;
    private Card menu2;
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
        TextView text = new TextView(getContext());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        text.setText("Change the given kanji word to hiragana");
        text.setLayoutParams(param);
        menu1.setContent(text);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WordToHiragana.class);
                startActivity(intent);
            }
        });

        menu2 = (Card) v.findViewById(R.id.comeSoonMenu);
        menu2.setTitle("");

        TextView text2 = new TextView(getContext());
        text2.setText("Come soon...");
        text2.setGravity(Gravity.CENTER_HORIZONTAL);
        text2.setLayoutParams(param);
        text2.setTextColor(Color.GRAY);
        menu2.setContent(text2);
    }
}

