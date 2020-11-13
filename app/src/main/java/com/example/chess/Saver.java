package com.example.chess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Saver {
    SQLiteBase helper;
    SQLiteDatabase db;

    public Saver(Context context){
        helper = new SQLiteBase(context);
    }

    public void saveFigures(List<Figure> list) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.beginTransaction();

        for(Figure figure: list) {
            values.put(SQLiteBase.FIELD_X, figure.getX());
            values.put(SQLiteBase.FIELD_Y, figure.getY());
            values.put(SQLiteBase.FIELD_COLOR, Character.toString(figure.getColor()));
            values.put(SQLiteBase.FIELD_TYPE, figure.toString());
            db.insert(helper.TABLE_NAME,null, values);
        }
        //db.endTransaction();
    }

    public void saveTurn(boolean isWhiteTurn) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(isWhiteTurn)
            values.put(SQLiteBase.FIELD_TURN, 1);
        else
            values.put(SQLiteBase.FIELD_TURN, 0);
        db.insert(SQLiteBase.TABLE_TURN, null, values);
    }

    public void clearBase() {
        db = helper.getWritableDatabase();
        db.delete(helper.TABLE_NAME, null, null);
        db.delete(helper.TABLE_TURN, null, null);
        db.delete("sqlite_sequence", null, null);
    }

    public List<Figure> loadFigures(char color) {
        db = helper.getReadableDatabase();
        List<Figure> toReturn = new ArrayList<>();
        String[] args = {Character.toString(color)};
        Cursor cursor = db.query("save",
                null,
                SQLiteBase.FIELD_COLOR + " = ?",
                args,
                null,
                null,
                null
        );
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                if(cursor.getString(4).equals("Rook"))
                    toReturn.add(new Rook(cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3).charAt(0)));

                else if(cursor.getString(4).equals("Knight"))
                    toReturn.add(new Knight(cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3).charAt(0)));

                else if(cursor.getString(4).equals("Bishop"))
                    toReturn.add(new Bishop(cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3).charAt(0)));

                else if(cursor.getString(4).equals("Queen"))
                    toReturn.add(new Queen(cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3).charAt(0)));

                else if(cursor.getString(4).equals("King"))
                    toReturn.add(new King(cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3).charAt(0)));

                else if(cursor.getString(4).equals("Pawn"))
                    toReturn.add(new Pawn(cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3).charAt(0)));
            }
            while(cursor.moveToNext());

            return toReturn;
        }
        return null;
    }

    public boolean LoadTurn() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("turn",
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(1) == 1;
        }
        return true;
    }

}
