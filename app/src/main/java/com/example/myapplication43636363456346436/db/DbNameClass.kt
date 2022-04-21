package com.example.myapplication43636363456346436.db

import android.provider.BaseColumns



object DbNameClass : BaseColumns {

    const val TABLE_NAME = "tn"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_TEXT = "text"

    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "TextDb.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT,$COLUMN_NAME_TEXT TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

}