package com.icaynia.tangomemory;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.icaynia.tangomemory.Models.word;

public class infActivity extends AppCompatActivity {
    private TextView hiraganavu;
    private TextView wordvu;
    private TextView koreanvu;
    private TextView count;
    private TextView editwordVu;
    private View dialogV;
    private int no;
    private word mword;

    private wordManager mWordManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf);
        this.init();
        Intent intent = getIntent();
        no = intent.getIntExtra("no", 0);

        if (no != 0) {
            mword = mWordManager.getWord(no);

            hiraganavu.setText(mword.hiragana);
            koreanvu.setText(mword.korean);
            wordvu.setText(mword.word);
            count.setText(mword.passcount+"/"+mword.showcount+" %");
        }
    }


    private void init() {
        hiraganavu = (TextView) findViewById(R.id.hiraganavu);
        wordvu = (TextView) findViewById(R.id.wordvu);
        koreanvu = (TextView) findViewById(R.id.koreanvu);
        count = (TextView) findViewById(R.id.count);
        editwordVu = (TextView) findViewById(R.id.editWordView);
        editwordVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditwordDialog();
            }
        });


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

    public void onEditwordDialog() {
        dialogV = getLayoutInflater().inflate(R.layout.dialog_addword, null);
        final AlertDialog.Builder   builder     = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        final EditText et_word = (EditText) dialogV.findViewById(R.id.et_word);
        et_word.setText(mword.word);
        final EditText et_hiragana = (EditText) dialogV.findViewById(R.id.et_hiragana);
        et_hiragana.setText(mword.hiragana);
        final EditText et_korean = (EditText) dialogV.findViewById(R.id.et_korean);
        et_korean.setText(mword.korean);
        final TextView warning = (TextView) dialogV.findViewById(R.id.warning);

        builder.setTitle("단어 편집하기");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!et_word.getText().toString().isEmpty()) {
                    mWordManager.updateWord(mword.id, et_word.getText().toString(), et_hiragana.getText().toString(), et_korean.getText().toString());
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
