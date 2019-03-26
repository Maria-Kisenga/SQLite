package com.example.katumbi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="users.db";
    public static final String TABLE_NAME="user_details";
    public static final String COL_1="_ID";
    public static final String COL_2="FirstName";
    public static final String COL_3="LastName";
    public static final String COL_4="Password";
    public static final String COL_5="Email";

    public static final String COL_6="Count";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,FirstName TEXT, LastName TEXT, Password TEXT, Email TEXT, Count INTEGER)");
        //add candidates automatically into table as soon as app is installed
        String sql = "INSERT or replace INTO user_details (FirstName, LastName, Password, Email, Count) VALUES('Anna','Banana','123','annabanana@gmail.com','0')" ;
        String sql2 = "INSERT or replace INTO user_details (FirstName, LastName, Password, Email, Count) VALUES('Elliot','Fish','123','elliotfish@gmail.com','0')" ;
        String sql3 = "INSERT or replace INTO user_details (FirstName, LastName, Password, Email, Count) VALUES('Jean','Jelly','123','jeanjelly@gmail.com','0')" ;

        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }

    public Cursor viewData () {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public boolean updateData(String person, long newCount) {
        SQLiteDatabase db = this.getWritableDatabase();

        //person = DBHelper.COL_2;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_6, newCount);

        db.update(TABLE_NAME, contentValues, "FirstName = ?", new String[] { person });
        return true;
    }
}

