package com.example.myapplication3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;

public class NotesDB extends SQLiteOpenHelper {

    public static  final String TABLE_NAME ="notes";
    public static  final String CONTENT="content";
    public static  final String ID="_id";
    public static  final String TIME ="time";
    public NotesDB(Context context,String name,SQLiteDatabase.CursorFactory factory,int version) {
        super(context, "notes", null, 1);
    }
    /*public NotesDB(Context context) {
        super(context, "notes", null, 1);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +"("
                + ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CONTENT + " TEXT NOT NULL,"
                + TIME + " TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
