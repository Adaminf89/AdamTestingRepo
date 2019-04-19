/*
Adam S Infiesto
Java 2
DatabaseHelper
* */
package com.example.adaminfiesto.infiestoadam_ce05.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_FILE = "database.db";
    private static final int DATABASE_VERSION = 1;


    private static final String
            CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            OutboundContract.DATA_TABLE+ " (" +
            OutboundContract.REDDIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OutboundContract.REDDIT_THUMBNAIL + " TEXT, " +
            OutboundContract.REDDIT_TITLE + " TEXT, " +
            OutboundContract.REDDIT_DESC + " TEXT)";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_FILE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    public void insertContent(ContentValues values)
    {
        getWritableDatabase().insert(OutboundContract.DATA_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}
