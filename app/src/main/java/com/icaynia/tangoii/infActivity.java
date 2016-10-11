package com.icaynia.tangoii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class infActivity extends AppCompatActivity {
    private TextView hiraganavu;
    private TextView wordvu;
    private TextView koreanvu;

    private wordManager mWordManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf);
        this.init();

        Intent intent = getIntent();
        int no = intent.getIntExtra("no", 0);

        //Toast.makeText(this, "no="+no, Toast.LENGTH_SHORT).show();

        if (no != 0) {
            word mword;
            mword = mWordManager.getWord(no);

            hiraganavu.setText(mword.hiragana);
            koreanvu.setText(mword.korean);
            wordvu.setText(mword.word);
        }
    }

    private void init() {
        hiraganavu = (TextView) findViewById(R.id.hiraganavu);
        wordvu = (TextView) findViewById(R.id.wordvu);
        koreanvu = (TextView) findViewById(R.id.koreanvu);

        mWordManager = new wordManager(this);
    }

    private void makeToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT);
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition( R.anim.anim_slide_in_left, R.anim.anim_slide_out_right );

    }


}
