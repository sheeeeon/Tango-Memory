package com.icaynia.tangoii;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by icaynia on 16. 10. 5..
 */
public class wordAdapter extends BaseAdapter{

    private ArrayList<word> m_List;

    public wordAdapter() {
        m_List = new ArrayList<word>();
    }

    public wordAdapter(ArrayList<word> _m_List) {
        m_List = _m_List;
        importList();
    }

    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_wordview, parent, false);

            // TextView에 현재 position의 문자열 추가
            TextView korean = (TextView) convertView.findViewById(R.id.korean);
            TextView word = (TextView) convertView.findViewById(R.id.wordtx);
            TextView hiragana = (TextView) convertView.findViewById(R.id.hiraganatx);

            korean.setText(m_List.get(position).korean);
            word.setText(m_List.get(position).word);
            hiragana.setText(m_List.get(position).hiragana);

            // 버튼을 터치 했을 때 이벤트 발생

            /*
            Button btn = (Button) convertView.findViewById(R.id.btn_test);
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, m_List.get(pos), Toast.LENGTH_SHORT).show();
                }
            });

            */

            // 리스트 아이템을 터치 했을 때 이벤트 발생
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                }
            });

            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            convertView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 롱 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        return convertView;
    }
    public void importList() {
        String TAG = "ww";
        for(word i : m_List){
            Log.d(TAG, "id = " + i.id);
            Log.d(TAG, "word = " + i.word);
        }
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String _word, String _hiragana, String _korean) {
        word nword = new word();
        nword.word = _word;
        nword.hiragana = _hiragana;
        nword.korean = _korean;
        m_List.add(nword);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }


}


//http://berabue.blogspot.kr/2014/05/android-listview.html