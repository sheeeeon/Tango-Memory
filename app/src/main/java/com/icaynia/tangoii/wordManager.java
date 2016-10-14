package com.icaynia.tangoii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by icaynia on 16. 10. 5..
 */
public class wordManager {

    String today = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(new Date());
    String yesterday;
    private MySQLiteOpenHelper helper;
    String dbName = "tangoii.db";
    int dbVersion = 2; // 데이터베이스 버전
    private SQLiteDatabase db;
    String tag = "SQLite"; // Log 에 사용할 tag
    Context context;

    ArrayList<word> wordList;


    private ListView m_ListView;
    private wordAdapter m_Adapter;


    public wordManager(Context _context) {
        context = _context;
        wordList = new ArrayList<word>();
        helper = new MySQLiteOpenHelper(
                context,
                dbName,
                null,
                dbVersion);

        try {

            db = helper.getWritableDatabase(); // 읽고 쓸수 있는 DB
            //db = helper.getReadableDatabase(); // 읽기 전용 DB select문
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(tag, "데이터베이스를 얻어올 수 없음");
            ((MainActivity)context).finish();
        }

        Calendar cal = new GregorianCalendar(Locale.KOREA);
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -1);
        yesterday = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(cal.getTime());
    }

    public void connectAdapter(View fv) {
        m_Adapter = new wordAdapter();
        m_ListView = (ListView) fv.findViewById(R.id.wordlistview);
        m_ListView.setAdapter(m_Adapter);

        this.listRefrash();
    }


    public word getWord(int index) {
        String sql = "select * from tangoii where id = "+index+";";
        Cursor result = db.rawQuery(sql, null);

        word mword = new word();

        // result(Cursor 객체)가 비어 있으면 false 리턴
        if(result.moveToFirst()){
            mword.id = result.getInt(0);
            mword.word = result.getString(2);
            mword.hiragana = result.getString(3);
            mword.korean = result.getString(4);
            mword.showcount = result.getInt(result.getColumnIndex("showcount"));
            mword.passcount = result.getInt(result.getColumnIndex("passcount"));

        }
        result.close();

        return mword;
    }

    public void addWord(String word, String hiragana, String korean) {
        db.execSQL("INSERT INTO tangoii " +
                "VALUES(null,null,'"+word+"','"+hiragana+"','"+korean+"',null,null,null,null,null,null,null,null,null,null,null,0,0,'"+today+"');"
        );
    }

    public void updateWord(int id, String word, String hiragana, String korean) {
        db.execSQL("UPDATE tangoii SET " +
                "word = '"+word+"', " +
                "hiragana = '"+hiragana+"', " +
                "korean = '"+korean+"' " +
                "WHERE id = '"+id+"';");
    }


    public void addCount(int id) {
        int count = this.getWord(id).showcount;
        count++;
        db.execSQL("UPDATE tangoii SET showcount = '"+count+"' WHERE id = '"+id+"';");
    }

    public void addPassCount(int id) {
        int count = this.getWord(id).passcount;
        count++;
        db.execSQL("UPDATE tangoii SET passcount = '"+count+"' WHERE id = '"+id+"';");
    }

    public int getWordRows() {

        String sql = "SELECT * FROM tangoii;";
        Cursor result = db.rawQuery(sql, null);

        int rows = result.getCount();

        return rows;
    }

    public boolean isAlreadyUsed(String wor) {
        boolean b = false;
        if (wordList.size() == 0) {
            wordList = getWordAll();
        }
        try {
            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = db.rawQuery("SELECT * FROM tangoii", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        word nword = new word();
                        nword.word = c.getString(2);
                        if (nword.word.equals(wor)) {
                            b = true;
                        }

                    } while (c.moveToNext());
                }
            }
            c.close();
        } catch (SQLiteException se) {
            Toast.makeText(context,  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
            ((MainActivity)context).finish();
        }
        return b;
    }

    public int getWordRowsToday() {
        String sql = "SELECT * FROM tangoii where regdate = '"+today+"'";

        Cursor result = db.rawQuery(sql, null);

        int rows = result.getCount();

        return rows;
    }

    public int getWordRowsYesterday() {

        String sql = "SELECT * FROM tangoii where regdate = '"+yesterday+"'";

        Cursor result = db.rawQuery(sql, null);

        int rows = result.getCount();

        return rows;

    }

    public boolean isLowCorrectPercentage (word mword) {
        if (getCorrectPercentage(mword) < 20) {
            return true;
        } else {
            return false;
        }
    }

    public int getCorrectPercentage(word mword) {
        int cutPercentLine = 20;         // max is 100.
        int showcount = mword.showcount;
        int passcount = mword.passcount;

        return showcount/passcount * 100;

    }

    public ArrayList<word> getWordAll() {
        ArrayList<word> array = new ArrayList<word>();
        try {
            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = db.rawQuery("SELECT * FROM tangoii", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        word nword = new word();
                        nword.id = c.getInt(c.getColumnIndex("id"));
                        nword.word = c.getString(2);
                        nword.hiragana = c.getString(3);
                        nword.korean = c.getString(4);
                        nword.korean2 = c.getString(5);
                        nword.showcount = c.getInt(c.getColumnIndex("showcount"));

                        //ArrayList에 추가합니다..
                        array.add(nword);

                    } while (c.moveToNext());
                }
            }
            c.close();
        } catch (SQLiteException se) {
            Toast.makeText(context,  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
            ((MainActivity)context).finish();
        }
        return array;
    }

    public void listRefrash() {
        //adapter 지우기
        m_Adapter.initList();

        try {
            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = db.rawQuery("SELECT * FROM tangoii", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        word nword = new word();
                        nword.id = c.getInt(c.getColumnIndex("id"));
                        nword.word = c.getString(2);
                        nword.hiragana = c.getString(3);
                        nword.korean = c.getString(4);
                        nword.korean2 = c.getString(5);
                        nword.showcount = c.getInt(c.getColumnIndex("showcount"));


                        m_Adapter.add(nword.word+"", nword.hiragana+"", nword.korean+"");

                        //ArrayList에 추가합니다..
                        wordList.add(nword);

                    } while (c.moveToNext());
                }
            }
            c.close();
        } catch (SQLiteException se) {
            Toast.makeText(context,  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }

    }

}


