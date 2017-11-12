package com.example.deh3215.sqliteexample2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    TextView tv1;
    //String str="";
    Context context;
    EditText name, score, _id;
    //ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        tv1 = (TextView)findViewById(R.id.textView1);
        _id = (EditText)findViewById(R.id.editText1);
        name = (EditText)findViewById(R.id.editText2);
        score = (EditText)findViewById(R.id.editText3);
//DB連線
        myDBHelper = new MyDBHelper(this);
        myDBHelper.onOpen(sqLiteDatabase);
//取得可寫入資料表
        myDBHelper.onOpen(sqLiteDatabase);
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        myDBHelper.onCreate(sqLiteDatabase);
//宣告android寫入資料庫物件
        //contentValues = new ContentValues();
//先讀出students裡面所有資料
        tv1.setText(showData(myDBHelper.getReadableDatabase(sqLiteDatabase)));
//關閉db連線和cursor物件,釋放記憶體
        //cursor.close();
        //sqLiteDatabase.close();
//顯示students資料表的資料
        //tv1.setText(str);
    }

    public String showData(Cursor cursor)   {
        String str="";
        if(cursor.getCount() != 0)  {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                str += cursor.getColumnName(0)+":" + cursor.getInt(0) + " "+cursor.getColumnName(1)+":"+
                                cursor.getString(1) + " "+cursor.getColumnName(2)+":" +  + cursor.getInt(2) + "\n";
                cursor.moveToNext();
            }
        }
        return str;
    }

    public boolean checkEmpty() {
        if(name.getText().toString().equals("") || score.getText().toString().equals("")) {
            Toast.makeText(context, "Not Empty !", Toast.LENGTH_SHORT).show();
            return true;
        }
            return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void clearText() {
        _id.setText("");
        name.setText("");
        score.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id)    {
            case R.id.action_add://ID會自動加1
                if(!checkEmpty()) {
                    myDBHelper.add(sqLiteDatabase, name.getText().toString(), Integer.valueOf(score.getText().toString()));
                    tv1.setText(showData(myDBHelper.getReadableDatabase(sqLiteDatabase)));
                    Toast.makeText(context, "Add ok", Toast.LENGTH_SHORT).show();
                    clearText();
                }
                break;
            case R.id.action_delete:
                //if(!checkEmpty()) {
                    myDBHelper.delete(sqLiteDatabase, Integer.valueOf(_id.getText().toString()));
                    cursor = myDBHelper.getReadableDatabase(sqLiteDatabase);
                    tv1.setText(showData(cursor));
                    Toast.makeText(context, "Delete ok", Toast.LENGTH_SHORT).show();
                    clearText();
                //}
                break;
            case R.id.action_edit:
                if(!checkEmpty()) {//Update 須帶ID
                    myDBHelper.update(sqLiteDatabase, Integer.valueOf(_id.getText().toString()), name.getText().toString(), Integer.valueOf(score.getText().toString()));
                    cursor = myDBHelper.getReadableDatabase(sqLiteDatabase);
                    tv1.setText(showData(cursor));
                    Toast.makeText(context, "Update ok", Toast.LENGTH_SHORT).show();
                    clearText();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
