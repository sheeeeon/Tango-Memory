package com.icaynia.tangoii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class WordToHiragana extends AppCompatActivity {
    private int count = 0;
    private TextView wordvu;
    private EditText input;
    private Button input_submit;
    private wordManager mWordManager;
    private Random oRandom;
    private ArrayList<word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_to_hiragana);
        this.init();

        game();
    }

    private void init() {
        mWordManager = new wordManager(this);
        wordvu = (TextView) findViewById(R.id.wordvu);
        input = (EditText) findViewById(R.id.input);
        input_submit = (Button) findViewById(R.id.input_submit);

        oRandom = new Random();
        words = mWordManager.getWordAll();

    }

    private void game() {
        //int randint = rand(words.size());
        int randint = words.size();
        word mword = words.get(randint);
        wordvu.setText(mword.word);

        mWordManager.addCount(mword.id);

        Log.e("count", "showcount = "+mword.showcount);


    }

    private int rand(int max) {
        int temp = oRandom.nextInt(max);
        return temp;
    }



}
