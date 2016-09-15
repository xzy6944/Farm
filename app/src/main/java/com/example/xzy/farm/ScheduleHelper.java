package com.example.xzy.farm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xzy on 2016/9/15.
 */
public class ScheduleHelper extends SQLiteOpenHelper {

    public static final String CREATE_SCHEDULE = "create table Schedule (" +
            "type integer, " +
            "time text, " +
            "details text, " +
            "read integer," +
            "primary key(type, time))";

    private Context mContext;

    public ScheduleHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCHEDULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
