package com.example.myapplication43636363456346436.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class DbManager(context: Context) {
    private val dbHelper = DbHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDb() {
        db = dbHelper.writableDatabase
    }

    fun insertToDb(title: String, text: String) {
        val values = ContentValues().apply {
            put(DbNameClass.COLUMN_NAME_TITLE, title)
            put(DbNameClass.COLUMN_NAME_TEXT, text)
        }
        db?.insert(DbNameClass.TABLE_NAME, null, values)
    }

    fun removeFromDb(id: String) {
        var selection = BaseColumns._ID + "=$id"
        db?.delete(DbNameClass.TABLE_NAME, selection, null)
    }

    fun updateDb(title: String, text:String, id: Int) {
        var selection = BaseColumns._ID + "=$id"
        val values = ContentValues().apply {
            put(DbNameClass.COLUMN_NAME_TITLE, title)
            put(DbNameClass.COLUMN_NAME_TEXT, text)
        }
        db?.update(DbNameClass.TABLE_NAME,  values, selection, null)
    }

    @SuppressLint("Range")
    fun readDbData(): ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(DbNameClass.TABLE_NAME, null, null, null, null, null, null)
        while (cursor?.moveToNext()!!) {
            val dataTitle = cursor.getString(cursor.getColumnIndex(DbNameClass.COLUMN_NAME_TITLE))
            val dataText = cursor.getString(cursor.getColumnIndex(DbNameClass.COLUMN_NAME_TEXT))
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            var item = ListItem()
            item.title = dataTitle
            item.text = dataText
            item.id = dataId
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        dbHelper.close()
    }

}