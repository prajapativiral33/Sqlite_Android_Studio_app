package com.example.viralprajapati.sqliteassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseClass extends SQLiteOpenHelper {

    public static final String Db_Name = "Db-name.db";
    public static final String T_Name = "info_table";
    public static final String Col_2 = "name";
    public static final String Col_3 = "email";
    public static final String Col_4 = "tvShow";
    public static final String ID = "id";


    public DatabaseClass(Context context) {
        super(context, Db_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + T_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email TEXT, FavAnimal TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + T_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String email, String favAnimal) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, email);
        contentValues.put(Col_4, favAnimal);
        long answer = sqLiteDatabase.insert(T_Name, null, contentValues);
        if(answer == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("select * from " + T_Name, null);
        return result;
    }

    public boolean updateData(String id, String name, String email, String favAnimal) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, email);
        contentValues.put(Col_4, favAnimal);
        sqLiteDatabase.update(T_Name, contentValues, "id = ?", new String[] { id });
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(T_Name, "ID = ?", new String[]{ id });
    }
}
