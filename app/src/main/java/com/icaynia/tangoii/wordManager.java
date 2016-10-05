package com.icaynia.tangoii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by icaynia on 16. 10. 5..
 */
public class wordManager {

    private MySQLiteOpenHelper helper;
    String dbName = "tangoii.db";
    int dbVersion = 1; // 데이터베이스 버전
    private SQLiteDatabase db;
    String tag = "SQLite"; // Log 에 사용할 tag
    Context context;


    private ListView m_ListView;
    private wordAdapter m_Adapter;


    public wordManager(Context _context) {
        context = _context;
        helper = new MySQLiteOpenHelper(
                context,    // 현재 화면의 제어권자
                dbName,     // db 이름
                null,       // 커서팩토리-null : 표준커서가 사용됨
                dbVersion); // 버전

        try {
//         // 데이터베이스 객체를 얻어오는 다른 간단한 방법
//         db = openOrCreateDatabase(dbName,  // 데이터베이스파일 이름
//                          Context.MODE_PRIVATE, // 파일 모드
//                          null);    // 커서 팩토리
//
//         String sql = "create table mytable(id integer primary key autoincrement, name text);";
//        db.execSQL(sql);

            db = helper.getWritableDatabase(); // 읽고 쓸수 있는 DB
            //db = helper.getReadableDatabase(); // 읽기 전용 DB select문
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(tag, "데이터베이스를 얻어올 수 없음");
            ((MainActivity)context).finish(); // 액티비티 종료
        }
    }

    public void connectAdapter(View fv) {
        // 커스텀 어댑터 생성
        m_Adapter = new wordAdapter();
        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) fv.findViewById(R.id.wordlistview);
        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView에 아이템 추가
        m_Adapter.add("하스스톤");
    }

    public word getWord(int index) {
        String sql = "select * from tangoii where id = "+index+";";
        Cursor result = db.rawQuery(sql, null);

        word mword = new word();

        // result(Cursor 객체)가 비어 있으면 false 리턴
        if(result.moveToFirst()){
            mword.id = result.getInt(0);

            mword.word = result.getString(2);

            Toast.makeText(context, "Rows="+getWordRows()+"index= "+mword.id+" voca="+mword.word, Toast.LENGTH_SHORT).show();
        }
        result.close();

        return mword;
    }

    public void addWord(String word) {
        db.execSQL("INSERT INTO tangoii " +
                "VALUES(null,null,'"+word+"',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);"
        );
    }

    public int getWordRows() {

        String sql = "SELECT * FROM tangoii;";
        Cursor result = db.rawQuery(sql, null);

        int rows = result.getCount();

        return rows;
    }
}
