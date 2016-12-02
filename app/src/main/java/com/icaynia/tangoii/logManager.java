package com.icaynia.tangoii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by icaynia on 2016. 10. 16..
 */
public class logManager {
    private Context context;
    private String dbName = "ti_game";
    private int dbVersion = 6;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;

    private String gameName = "";
    private int showCount = 0;
    private int passCount = 0;

    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ,Locale.KOREA);

    String startTime;
    String endTime;
    long Timesl;

    public logManager(Context _Context) {
        context = _Context;
        helper = new MySQLiteOpenHelper(
                context,
                dbName,
                null,
                dbVersion);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e("DB:ti_game", "데이터베이스를 얻어올 수 없음");
            ((MainActivity) context).finish();
        }
    }

    public void setGameName(String _gameName) {
        gameName = _gameName;
    }

    public void addShowCount() {
        showCount++;
    }

    public void addPassCount() {
        passCount++;
    }

    public void timeStart() {
        startTime = transFormat.format(new Date());
    }


    public void timeEnd() {
        endTime = transFormat.format(new Date());

    }

    public void addlog() {
        db.execSQL("INSERT INTO ti_game " +
                "VALUES(null,'"+gameName+"','null','"+showCount+"','"+passCount+"','"+startTime+"','"+endTime+"');");

    }

    public int getLog() {

        String sql = "select * from ti_game;";
        Cursor result = db.rawQuery(sql, null);

        if (result.moveToFirst()) {
            do {
                Log.e("gameLog", "id = " + result.getInt(0)
                        + " category " + result.getString(1)
                        + " count " + result.getInt(2)
                        + " scount " + result.getInt(3)
                        + " ocount " + result.getInt(4)
                        + " start " + result.getString(5)
                        + " end " + result.getString(6));
            } while (result.moveToNext());

        }
        return 0;
    }

    public int getCount(String day) {
        String sql = "select * from ti_game where date(time_start) = '"+day+"'";
        int cnt = 0;
        Log.e("gg", sql);
        Cursor result = db.rawQuery(sql,null);

        if (result.moveToFirst()) {
            do {
                cnt += result.getInt(3);
            } while (result.moveToNext());
        }
        return cnt;
    }

    public int getCount() {
        String sql = "select * from ti_game";
        int cnt = 0;
        Cursor result = db.rawQuery(sql,null);

        if (result.moveToFirst()) {
            do {
                cnt += result.getInt(3);
            } while (result.moveToNext());
        }
        return cnt;
    }




}
