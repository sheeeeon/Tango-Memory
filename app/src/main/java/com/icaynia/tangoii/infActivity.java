package com.icaynia.tangoii;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class infActivity extends AppCompatActivity {
    private TextView hiraganavu;
    private TextView wordvu;
    private TextView koreanvu;
    private TextView count;
    private View dialogV;

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
            count.setText(mword.passcount+"/" +mword.showcount);
        }
    }

    private void init() {
        hiraganavu = (TextView) findViewById(R.id.hiraganavu);
        wordvu = (TextView) findViewById(R.id.wordvu);
        koreanvu = (TextView) findViewById(R.id.koreanvu);
        count = (TextView) findViewById(R.id.count);

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

    public void onEditwordDialog(final word beforeWord) {
        dialogV = getLayoutInflater().inflate(R.layout.dialog_addword, null);
        final AlertDialog.Builder   builder     = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        final EditText et_word = (EditText) dialogV.findViewById(R.id.et_word);
        et_word.setText(beforeWord.word);
        final EditText et_hiragana = (EditText) dialogV.findViewById(R.id.et_hiragana);
        et_hiragana.setText(beforeWord.hiragana);
        final EditText et_korean = (EditText) dialogV.findViewById(R.id.et_korean);
        et_korean.setText(beforeWord.korean);
        final TextView warning = (TextView) dialogV.findViewById(R.id.warning);


        et_word.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mWordManager.isAlreadyUsed(et_word.getText().toString())) {
                        warning.setText("This word is already used.");
                        Log.e("onFocusChange","used");
                    } else {
                        warning.setText("");
                    }
                }
            }
        });

        builder.setTitle("단어 추가하기");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!et_word.getText().toString().isEmpty() && !mWordManager.isAlreadyUsed(et_word.getText().toString())) {
                    mWordManager.updateWord(beforeWord.id, et_word.getText().toString(), et_hiragana.getText().toString(), et_korean.getText().toString());
                    mWordManager.listRefrash();
                    dialog.dismiss();
                }


            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //((MainActivity)getContext()).makeToast("Scene 작성을 취소하였습니다.");
                dialog.dismiss();
            }
        });

        builder.setView(dialogV);
        //데이터 관련


        final AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();    // 알림창 띄우기

    }


}
