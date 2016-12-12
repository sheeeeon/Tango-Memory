package com.icaynia.tangomemory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by icaynia on 2016. 12. 3..
 */

public class Splash extends AppCompatActivity {
    SharedPreferences mPref = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirst = true;
                        //mPref.getBoolean("isFirst", true);
                if (isFirst == true) {
                    SharedPreferences.Editor prefEditor = mPref.edit();
                    prefEditor.putBoolean("isFirst", false);
                    prefEditor.apply();

                    onTutorialActivity();
                } else {
                    onMainActivity();
                }

                finish();
            }
        }, 3000);

        mPref = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        //mPref.registerOnSharedPreferenceChangeListener(mPrefChangeListener);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onMainActivity() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public void onTutorialActivity () {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

}
