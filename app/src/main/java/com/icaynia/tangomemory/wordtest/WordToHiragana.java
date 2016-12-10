package com.icaynia.tangomemory.wordtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.Data.logManager;
import com.icaynia.tangomemory.Models.word;
import com.icaynia.tangomemory.Data.wordManager;

import java.util.ArrayList;
import java.util.Random;

public class WordToHiragana extends AppCompatActivity {
    private int count = 0;
    private int errorcount = 0;
    private TextView wordvu;
    private TextView hiraganavu;
    private TextView errorcountvu;
    private EditText input;
    private TextView giveupvu;
    private Button input_submit;
    private wordManager mWordManager;
    private Random oRandom;
    private ArrayList<word> words;
    private word mword;
    private Handler mHandler;
    private word previousword = new word();
    private customThread mThread = new customThread();
    private logManager mLogManager;
    private MenuItem giveupItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_to_hiragana);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        this.init();
        Log.e("word.size", words.size() + "");
        if (words.size() >= 5) {
            game();
        } else {
            // 단어가 전체 5개 이상이 아닐 시
            finish();
        }
    }

    @Override
    protected void onStop() {
        mLogManager.timeEnd();
        super.onStop();
        mLogManager.addlog();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game, menu);

        giveupItem = menu.findItem(R.id.action_giveup);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_giveup:
                giveup();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        mWordManager = new wordManager(this);
        wordvu = (TextView) findViewById(R.id.wordvu);
        hiraganavu = (TextView) findViewById(R.id.hiraganavu);
        input = (EditText) findViewById(R.id.input);
        input_submit = (Button) findViewById(R.id.input_submit);
        errorcountvu = (TextView) findViewById(R.id.errorcount);
        oRandom = new Random();
        words = mWordManager.getWordAll();
        mHandler = new Handler();
        log_init();
        mLogManager.getLog();

    }

    private void log_init() {
        mLogManager = new logManager(this);
        mLogManager.setGameName("WordToHiragana");
        mLogManager.timeStart();
    }

    private void game() {
        count++;

        //단어 선택
        mword = getRandWord();
        if (!isKanji(mword.word)) {
            game();
            return;
        }

        final int r_id = mword.id;
        errorcountvu.setText("Life = " + (3-errorcount));
        wordvu.setText(mword.word);
        mLogManager.addShowCount();
        mWordManager.addCount(r_id);

        Log.e("count", "showcount = "+mword.showcount);
        input_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().equals(mword.hiragana)) {
                    mLogManager.addPassCount();
                    hiraganavu.setText(mword.word+"["+mword.hiragana+"] "+mword.korean);
                    mWordManager.addPassCount(r_id);
                    hiraganavu.setTextColor(getResources().getColor(android.R.color.black));
                    errorcount = 0;
                    input.setText("");
                    game();
                } else {
                    errorcount++;
                    errorcountvu.setText("Life = " + (3-errorcount));
                    if (errorcount >= 3) {
                        errorcount = 0;
                        hiraganavu.setText(mword.word+"["+mword.hiragana+"] "+mword.korean);
                        hiraganavu.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        input.setText("");
                        game();
                    }
                }
            }
        });
    }

    private void giveup() {
        giveupItem.setVisible(false);
        errorcount = 3;
        input_submit.callOnClick();
        mThread.run();
    }


    private int rand(int max) {
        int temp = oRandom.nextInt(max);
        if (temp <= 0) {
            rand(max);
        }
        return temp;
    }

    public static boolean isKanji(String str)
    {
        for(int i = 0 ; i < str.length() ; i++)
        {
            char ch = str.charAt(i);
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
            if(
                    Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(unicodeBlock) ||
                    Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(unicodeBlock) ||
                    Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B.equals(unicodeBlock) ||
                    Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(unicodeBlock) ||
                    Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT.equals(unicodeBlock)
            ) return true;
        }
        return false;
    }

    public word getRandWord() {
        int randint;
        word returnWord;

        while (true) {
            randint = rand(words.size());
            returnWord = words.get(randint);

            if (isKanji(returnWord.word)) {
                if (!returnWord.word.equals(previousword.word) || previousword.word.equals("")) {
                    previousword = returnWord;
                    break;
                }
            }
            break;
        }

        return returnWord;
    }

    private class customThread extends Thread {

        public customThread() {
        }

        @Override
        public void run() {
            super.run();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    giveupItem.setVisible(true);
                }
            }, 1000);

        }

    }


}
