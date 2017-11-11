package com.example.deh3215.sqliteexample2;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deh3215 on 2017/11/11.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    final private static int DB_VERSION = 1;
    final private static String DB_DATABASE_NAME = "mydata.db";// Android資料庫預設路徑放在 /data/data/packagename/databases/底下
    final private static String DB_TABLE_NAME = "students";

//    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
    public MyDBHelper(Context context) {
        super(context, DB_DATABASE_NAME, null, DB_VERSION);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ DB_TABLE_NAME +"(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, score INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NAME);
        onCreate(db);
    }

    public Cursor getReadableDatabase(SQLiteDatabase db) {
        return db.rawQuery("SELECT * FROM "+DB_TABLE_NAME, null);
    }
}