package com.collagepoem.app

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    private val createNotes = "create table Notes (" +
            " id integer primary key autoincrement," +
            "author text," +
            "bookname text," +
            "collecttime text,"+
            "country text,"+
            "state int,"+
            "Mentioned int,"+
            "Cited int,"+
            "image blob)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createNotes)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}