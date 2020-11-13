package com.example.chess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "base.db";
    public static final String TABLE_NAME = "save";
    public static final String TABLE_TURN = "turn";
    public static final String FIELD_X = "xField";
    public static final String FIELD_Y = "yField";
    public static final String FIELD_COLOR = "cField";
    public static final String FIELD_TYPE = "tField";
    public static final String FIELD_TURN = "turnField";


    public SQLiteBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_X + " INTEGER, "+
                FIELD_Y + " INTEGER, "+
                FIELD_COLOR + " TEXT, "+
                FIELD_TYPE + " TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TURN +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_TURN + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TURN);
        onCreate(sqLiteDatabase);
    }
}
