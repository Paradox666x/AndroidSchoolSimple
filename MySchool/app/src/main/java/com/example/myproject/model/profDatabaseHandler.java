package com.example.myproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class profDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbaseprof.db";
    //table 1
    private static final String COLUMN_NAME = "ProfNAME";
    private static final String COLUMN_LNAME = "ProfLNAME";
    private static final String COLUMN_EMAIL = "ProfPseudo";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private static final String TABLE_NAME = "Prof";




    public profDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String req = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_NAME + " TEXT, " + COLUMN_LNAME + " TEXT, " + COLUMN_EMAIL + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT) ";
        db.execSQL(req);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void insertprof(String ProfNAME, String ProfLNAME, String ProfPseudo, String PASSWORD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, ProfNAME);
        values.put(COLUMN_LNAME, ProfLNAME);
        values.put(COLUMN_EMAIL, ProfPseudo);
        values.put(COLUMN_PASSWORD, PASSWORD);
        db.insert(TABLE_NAME, null, values);

    }








    public String getprof(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_NAME, COLUMN_LNAME, COLUMN_EMAIL, COLUMN_PASSWORD};
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, pass};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String result = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3);
            }
            cursor.close();
        }

        return result;
    }


    public String getprof(String pseudo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_NAME, COLUMN_LNAME, COLUMN_EMAIL, COLUMN_PASSWORD};
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {pseudo};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String result = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3);
            }
            cursor.close();
        }

        return result;
    }


    public boolean verifyprof(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_EMAIL, COLUMN_PASSWORD};
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, pass};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean result = false;
        if (cursor != null && cursor.getCount() > 0) { // check if the cursor is not null and has at least one row
            result = true;
            cursor.close();
        }

        return result;
    }


    public boolean verifyprofPseudo(String Pseudo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_EMAIL};
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {Pseudo};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean result = false;
        if (cursor != null) {
            result = cursor.moveToFirst();
            cursor.close();
        }

        return result;
    }





}
