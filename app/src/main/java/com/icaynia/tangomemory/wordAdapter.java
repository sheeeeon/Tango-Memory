package com.icaynia.tangomemory;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.icaynia.tangomemory.Models.word;

import java.util.ArrayList;

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
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_wordview, parent, false);
        }
            ViewHolder vh = new ViewHolder();

            vh.korean = (TextView) convertView.findViewById(R.id.korean);
            vh.word = (TextView) convertView.findViewById(R.id.wordtx);
            vh.hiragana = (TextView) convertView.findViewById(R.id.hiraganatx);

            convertView.setTag(vh);

            word n = m_List.get(position);
            if (n != null) {
                ViewHolder holder = (ViewHolder) convertView.getTag();
                holder.korean.setText(n.korean);
                holder.word.setText(n.word);
                holder.hiragana.setText(n.hiragana);
            }

            // 버튼을 터치 했을 때 이벤트 발생



            // 리스트 아이템을 터치 했을 때 이벤트 발생
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    //Toast.makeText(context, "리스트 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, infActivity.class);
                    intent.putExtra("no", pos+1);
// 두번째 액티비티를 실행하기 위한 인텐트
                    context.startActivity(intent);
                    //((MainActivity)context).
                            //overridePendingTransition( R.anim.anim_slide_in_left , R.anim.anim_slide_out_right);

                }
            });

            /*
            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            convertView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 롱 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            */


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

    public void initList() {
        m_List = new ArrayList<word>();
    }

    static class ViewHolder {
        TextView korean;
        TextView word;
        TextView hiragana;

    }


}




//http://berabue.blogspot.kr/2014/05/android-listview.html