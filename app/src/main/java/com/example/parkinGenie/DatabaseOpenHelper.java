package com.example.parkinGenie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "parkinGenie.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseOpenHelper(Context context, String name, String storageDirectory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




}