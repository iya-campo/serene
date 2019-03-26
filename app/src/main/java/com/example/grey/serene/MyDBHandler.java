package com.example.grey.serene;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

import android.content.ContentValues;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{
    //information of database

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Serene.db";

    private static final String SQL_CREATE_USERS_TABLE =
            "CREATE TABLE " + DatabaseContract.Users_Info.TABLE_NAME + " (" +
                    DatabaseContract.Users_Info.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.Users_Info.COLUMN_NAME + " TEXT NOT NULL, " +
                    DatabaseContract.Users_Info.COLUMN_EMAIL + " TEXT NOT NULL, " +
                    DatabaseContract.Users_Info.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                    DatabaseContract.Users_Info.COLUMN_NICKNAME + " TEXT NOT NULL, " +
                    DatabaseContract.Users_Info.COLUMN_AGE + " INTEGER NOT NULL, " +
                    DatabaseContract.Users_Info.COLUMN_ALARM + " TEXT NOT NULL, "+
                    DatabaseContract.Users_Info.COLUMN_NOTIFICATIONS + " TEXT NOT NULL " +"); ";

    private static final String SQL_CREATE_ARTICLES_TABLE =
            "CREATE TABLE " + DatabaseContract.Articles.TABLE_NAME + " (" +
                    DatabaseContract.Articles.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.Articles.COLUMN_TITLE + " TEXT NOT NULL, " +
                    DatabaseContract.Articles.COLUMN_DATE + " DATE NOT NULL, " +
                    DatabaseContract.Articles.COLUMN_CONTENTS + " TEXT NOT NULL " +"); ";


    private static final String SQL_CREATE_JOURNAL_TABLE =
            "CREATE TABLE " + DatabaseContract.Journal.TABLE_NAME + " (" +
                    DatabaseContract.Journal.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.Journal.COLUMN_USERID + " INTEGER NOT NULL, " +
                    DatabaseContract.Journal.COLUMN_SLEEPHOURS + " INTEGER NOT NULL, " +
                    DatabaseContract.Journal.COLUMN_FOODINTAKE + " INTEGER NOT NULL, " +
                    DatabaseContract.Journal.COLUMN_MEDICINALTAKE + " INTEGER NOT NULL, " +
                    DatabaseContract.Journal.COLUMN_DATE + " DATE NOT NULL "  +"); ";

    private static final String SQL_CREATE_USERSFRIEND_TABLE =
            "CREATE TABLE " + DatabaseContract.Users_Friends.TABLE_NAME + " (" +
                    DatabaseContract.Users_Friends.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.Users_Friends.COLUMN_USERID + " TEXT NOT NULL, " +
                    DatabaseContract.Users_Friends.COLUMN_NAME + " TEXT NOT NULL, " +
                    DatabaseContract.Users_Friends.COLUMN_EMAIL + " INTEGER NOT NULL " +"); ";

    //initialize the database


    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_ARTICLES_TABLE);
        db.execSQL(SQL_CREATE_JOURNAL_TABLE);
        db.execSQL(SQL_CREATE_USERSFRIEND_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.Users_Info.TABLE_NAME);

        onCreate(db);
    }

    public String loadHandler() {

        String result = "";

        String query = "SELECT * FROM " + DatabaseContract.Users_Info.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            int result_0 = cursor.getInt(0);

            String result_1 = cursor.getString(1);

            String result_2 = cursor.getString(2);

            result +=String.valueOf(result_0) + " ---> " + result_1 +"---> "+ result_2+ System.getProperty("line.separator");

        }

        cursor.close();

        db.close();

        return result;

    }
    /*
    public void addHandler(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, users.getID());

        values.put(COLUMN_NAME, users.getUsersName());

        values.put(COLUMN_EMAIL, users.getEmail());

        values.put(COLUMN_NICKNAME, users.getUserNickName());

        values.put(COLUMN_AGE, users.getAge());

        values.put(COLUMN_ALARM, users.getAlarm());

        values.put(COLUMN_NOTIFICATIONS, users.getNotifications());

        values.put(COLUMN_PASSWORD, users.getPassword());

        db.insert(TABLE_NAME, null, values);

        db.close();

    }

    public Users findHandler(String usersname) {

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=" + "'" + usersname + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Users users = new Users();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            users.setID(Integer.parseInt(cursor.getString(0)));
            users.setUsersName(cursor.getString(1));
            users.setEmail(cursor.getString(2));

            cursor.close();

        } else {

            users = null;

        }

        db.close();

        return users;

    }

    public boolean deleteHandler(int ID) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + String.valueOf(ID) + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Users user = new Users();

        if (cursor.moveToFirst()) {

            user.setID(Integer.parseInt(cursor.getString(0)));

            db.delete(TABLE_NAME, COLUMN_ID + "=?",

                    new String[] {

                            String.valueOf(user.getID())

                    });

            cursor.close();

            result = true;

        }

        db.close();

        return result;

    }

    public boolean updateHandler(int ID, String name, String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(COLUMN_ID, ID);

        args.put(COLUMN_NAME, name);

        args.put(COLUMN_EMAIL, email);

        //args.put();

        return db.update(TABLE_NAME, args, COLUMN_ID + " = " + ID, null) > 0;

    }

    public List<Users> getAllUsersList(){
        List<Users> usersList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setID(Integer.parseInt(cursor.getString(0)));
                users.setUsersName(cursor.getString(1));
                users.setPassword(cursor.getString(2));

                usersList.add(users);
            }while(cursor.moveToNext());
        }

        return usersList;
    }

    public long CountRows(){

        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }

    public boolean Login(String username, String password){
        boolean login = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=" + "'" + username + "'" + COLUMN_PASSWORD + "=" + "'" + password + "'";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            login = true;
            cursor.close();
        }

        db.close();
        return login;
    }
    */


}



