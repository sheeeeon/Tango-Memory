package com.icaynia.tangoii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class WordToHiragana extends AppCompatActivity {
    private int count = 0;
    private TextView wordvu;
    private TextView koreanvu;
    private EditText input;
    private wordManager mWordManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_to_hiragana);
        this.init();
    }

    private void init() {
        mWordManager = new wordManager(this);

    }



}
