package com.example.deh3215.sqliteexample2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    TextView tv1;
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.textView1);
//DB連線
        myDBHelper = new MyDBHelper(this);
        myDBHelper.onOpen(sqLiteDatabase);
//取得可寫入資料表
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        myDBHelper.onCreate(sqLiteDatabase);
//宣告android寫入資料庫物件
        ContentValues contentValues = new ContentValues();
//寫入資料表的資料
        contentValues.put("name", "Jimmy");
        contentValues.put("score", "100");
        sqLiteDatabase.insert("students", null, contentValues);
//取得查詢結果
        cursor = myDBHelper.getReadableDatabase(sqLiteDatabase);
//取得欄位數量和利用cursor物件依序取得資料
        if(cursor.getCount() != 0)  {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                str += cursor.getColumnName(0)+":" + cursor.getInt(0) + " "+cursor.getColumnName(1)+":"+
                                cursor.getString(1) + " "+cursor.getColumnName(2)+":" +  + cursor.getInt(2) + "\n";
                cursor.moveToNext();
            }
        }
//關閉db連線和cursor物件,釋放記憶體
        cursor.close();
        sqLiteDatabase.close();
//顯示students資料表的資料
        tv1.setText(str);
    }
}
