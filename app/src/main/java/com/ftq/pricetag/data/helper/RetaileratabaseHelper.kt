package com.ftq.pricetag.data.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RetaileratabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_RETAILER_TABLE = ("CREATE TABLE $TABLE_RETAILER("
                + "$COLUMN_ID INTEGER PRIMARY KEY,"
                + "$COLUMN_NAME TEXT)")
        db.execSQL(CREATE_RETAILER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RETAILER")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "retailerManager.db"
        const val TABLE_RETAILER = "retailer"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }
}
