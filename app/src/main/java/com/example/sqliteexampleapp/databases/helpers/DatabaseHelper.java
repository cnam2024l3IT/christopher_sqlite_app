package com.example.sqliteexampleapp.databases.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqliteexampleapp.databases.contracts.DataContract;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "example_sqlite.db";

    static final int DB_VERSION = 5;

    public DatabaseHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataContract.PersonneTable.SQL_CREATE);
        db.execSQL(DataContract.AnnonceTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataContract.AnnonceTable.SQL_DELETE);
        db.execSQL(DataContract.PersonneTable.SQL_DELETE);
        onCreate(db);
    }
}
