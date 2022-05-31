package com.example.sqlite_191103538;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_controller extends SQLiteOpenHelper {
    public DB_controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
      super(context, "TEST.db", factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT ");
        onCreate(sqLiteDatabase);

    }

    public void insert_student(String firstname, String lastname){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        this.getWritableDatabase().insertOrThrow("STUDENT", "",contentValues);
    }
    public void delete_student(String firstname){
        this.getWritableDatabase().delete("STUDEMT", "FIRSTNAME='"+firstname+"'", null);
    }
    public void list_all_student(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENT", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }

    public void update_student(String old_firstName, String new_firstName){
        this.getWritableDatabase().execSQL("UPDATE STUDENT FIRSTNAME = '"+new_firstName+"'FIRSTNAME = '"+old_firstName+"'");
    }
}
