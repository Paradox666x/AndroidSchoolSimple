package com.example.myproject.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbase.db";//table 1
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_LNAME = "LNAME";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private static final String TABLE_NAME = "USER";
//table 2

    private static final String COLUMN_MODULE_NAME = "MODULE_NAME";
    private static final String COLUMN_NOTE_1 = "NOTE_1";
    private static final String COLUMN_NOTE_2 = "NOTE_2";
    private static final String COLUMN_NOTE_3 = "NOTE_3";
    private static final String TABLE_MODULES = "MODULES";
    private static final String COLUMN_USER_EMAIL = "USER_EMAIL";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String req = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_NAME + " TEXT, " + COLUMN_LNAME + " TEXT, " + COLUMN_EMAIL + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT) ";
        db.execSQL(req);

        req = "CREATE TABLE " + TABLE_MODULES + " ( " + COLUMN_MODULE_NAME + " TEXT, " + COLUMN_NOTE_1 + " REAL, " + COLUMN_NOTE_2 + " REAL, " + COLUMN_NOTE_3 + " REAL, " + COLUMN_USER_EMAIL + " TEXT, FOREIGN KEY(" + COLUMN_USER_EMAIL + ") REFERENCES " + TABLE_NAME + "(" + COLUMN_EMAIL + "))";
        db.execSQL(req);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String name, String lname, String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_LNAME, lname);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, pass);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void insertModule(String moduleName, double note1, double note2, double note3, String userEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MODULE_NAME, moduleName);
        values.put(COLUMN_NOTE_1, note1);
        values.put(COLUMN_NOTE_2, note2);
        values.put(COLUMN_NOTE_3, note3);
        values.put(COLUMN_USER_EMAIL, userEmail);
        db.insert(TABLE_MODULES, null, values);
        db.close();
    }

    public String getModuleData(String userEmail, String moduleName) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COLUMN_MODULE_NAME, COLUMN_NOTE_1, COLUMN_NOTE_2, COLUMN_NOTE_3};
        String selection = COLUMN_USER_EMAIL + " = ? AND " + COLUMN_MODULE_NAME + " = ?";
        String[] selectionArgs = {userEmail, moduleName};
        Cursor cursor = db.query("MODULES", columns, selection, selectionArgs, null, null, null);

        StringBuilder moduleData = new StringBuilder();
        if (cursor.moveToFirst()) {
            int note1Index = cursor.getColumnIndex(COLUMN_NOTE_1);
            int note2Index = cursor.getColumnIndex(COLUMN_NOTE_2);
            int note3Index = cursor.getColumnIndex(COLUMN_NOTE_3);

            // Check that the column indexes are valid
            if (note1Index >= 0 && note2Index >= 0 && note3Index >= 0) {
                int note1 = cursor.getInt(note1Index);
                int note2 = cursor.getInt(note2Index);
                int note3 = cursor.getInt(note3Index);

                moduleData.append("Module: ").append(moduleName).append("\n");
                moduleData.append("Note 1: ").append(note1).append("\n");
                moduleData.append("Note 2: ").append(note2).append("\n");
                moduleData.append("Note 3: ").append(note3).append("\n");
            }
        }
        cursor.close();
        db.close();
        return moduleData.toString();
    }






    public String getUser(String email, String pass) {
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
        db.close();
        return result;
    }

    public boolean verifyUser(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_EMAIL, COLUMN_PASSWORD};
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, pass};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean result = false;
        if (cursor != null) {
            result = cursor.moveToFirst();
            cursor.close();
        }
        db.close();
        return result;
    }

    public String getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_NAME, COLUMN_LNAME, COLUMN_EMAIL, COLUMN_PASSWORD};
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String result = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) ;
            }
            cursor.close();
        }
        db.close();
        return result;
    }



    public void updateModule(String moduleName, double note1, double note2, double note3, String userEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_1, note1);
        values.put(COLUMN_NOTE_2, note2);
        values.put(COLUMN_NOTE_3, note3);
        String whereClause = COLUMN_USER_EMAIL + " = ? AND " + COLUMN_MODULE_NAME + " = ?";
        String[] whereArgs = {userEmail, moduleName};
        db.update(TABLE_MODULES, values, whereClause, whereArgs);
        db.close();
    }




    public String getUserString(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_NAME, COLUMN_LNAME, COLUMN_EMAIL, COLUMN_PASSWORD};
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        String result = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = "Nom :"+cursor.getString(0) + " \nPrenom : " + cursor.getString(1) + "\nClass : DEVAOM201 " ;
            }
            cursor.close();
        }
        db.close();
        return result;
    }

    public String getAllModule(String user_email) {

        double FinalResult=0;
        int modulecount=0;

        String result = getUser(user_email);


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " +
                "USER.NAME, USER.LNAME, USER.EMAIL, MODULES.MODULE_NAME, MODULES.NOTE_1, MODULES.NOTE_2, MODULES.NOTE_3 " +
                "FROM " +
                "USER " +
                "INNER JOIN MODULES " +
                "ON USER.EMAIL = MODULES.USER_EMAIL " +
                "WHERE USER.EMAIL=?", new String[]{user_email});

        if (cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") String moduleName = cursor.getString(cursor.getColumnIndex("MODULE_NAME"));
                @SuppressLint("Range") double note1 = cursor.getDouble(cursor.getColumnIndex("NOTE_1"));
                @SuppressLint("Range") double note2 = cursor.getDouble(cursor.getColumnIndex("NOTE_2"));
                @SuppressLint("Range") double note3 = cursor.getDouble(cursor.getColumnIndex("NOTE_3"));


                modulecount++;
                FinalResult+=(note1+note2+note3)/3;


                result +=
                        "Module Name: " + moduleName + "\n" +
                        "Note 1: " + note1 + "\n" +
                        "Note 2: " + note2 + "\n" +
                        "Note 3: " + note3 + "\n"+
                         "Moyen :"+(note1+note2+note3/3)+ "\n\n" ;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ""+FinalResult/modulecount;
    }









}
