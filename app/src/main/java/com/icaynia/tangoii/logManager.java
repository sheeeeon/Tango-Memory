package com.icaynia.tangoii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by icaynia on 2016. 10. 16..
 */
public class logManager {
    private Context context;
    private String dbName = "ti_game";
    private int dbVersion = 3;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;

    private String gameName = "";
    private int showCount = 0;
    private int passCount = 0;


    String startTime;
    String endTime;

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
        startTime = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.KOREA).format(new Date());
    }

    public void timeEnd() {
        endTime = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.KOREA).format(new Date());
    }

    public void addlog() {
        db.execSQL("INSERT INTO ti_game " +
                "VALUES(null, " +
                "'" + gameName + "',null," +
                "'" + showCount + "','" + passCount + "'," +
                "'" + startTime + "','" + endTime + "');"
        );
    }
}
