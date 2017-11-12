package com.example.deh3215.sqliteexample2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;

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

    String[] str = new String[] {"_id", "name", "score"};

    public Cursor getReadableDatabase(SQLiteDatabase db) {
        return db.query(DB_TABLE_NAME,str,null,null,null,null,null);
    }

    public int delete(SQLiteDatabase db, Integer id)   {
        return db.delete(DB_TABLE_NAME,"_id" + "='"+id+"'",null);
    }

    public long add(SQLiteDatabase db, String name, Integer score)  {
        //return db.rawQuery("INSERT INTO "+DB_TABLE_NAME+" WHERE name='"+name+"' AND score='"+score+"'", null);
        //寫入資料表的資料
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("score", score);
        return db.insert(DB_TABLE_NAME, null, cv);
    }

    public int update(SQLiteDatabase db, Integer id, String name, Integer score) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("score", score);
        return  db.update(DB_TABLE_NAME, cv,  "_id" + "=" + id, null);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}