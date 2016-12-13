package com.icaynia.tangomemory.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.icaynia.tangomemory.Data.wordAdapter;
import com.icaynia.tangomemory.Data.wordManager;
import com.icaynia.tangomemory.Models.word;
import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.TutorialActivity;

import java.util.ArrayList;

/**
 * Created by icaynia on 2016. 12. 6..
 */

public class WordFragment extends android.support.v4.app.Fragment {
    private View fragmentView;
    private wordManager mWordManager;

    private ListView list;
    private wordAdapter mWordAdapter;

    private LinearLayout noword;

    public WordFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_word, container, false);
        initialize();
        return fragmentView;

    }

    private void initialize() {
        mWordManager = new wordManager(getContext());
        ArrayList<word> array = mWordManager.getWordAll();
        mWordAdapter = new wordAdapter(array);
        list = (ListView) fragmentView.findViewById(R.id.fragment_word_listview);
        list.setAdapter(mWordAdapter);
        noword = (LinearLayout) fragmentView.findViewById(R.id.noword);

        if (array.size() == 0) {
            noword.setVisibility(View.VISIBLE);
        } else {
            noword.setVisibility(View.GONE);
        }

    }
}
