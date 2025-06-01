package br.com.SylTech.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DBNAME, null, DBVERSION ) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_notes)
        db?.execSQL(CREATE_TABLE_Colecao)
        db?.execSQL(CREATE_TABLE_Reminder)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(newVersion > oldVersion) {
            db?.execSQL(DROP_TABLE_notes)
            db?.execSQL(DROP_TABLE_Colecao)
            db?.execSQL(DROP_TABLE_Reminder)
        }
    }

    companion object {
        private val DBNAME = "Syltech_Database"
        private val DBVERSION = 1
        private val CREATE_TABLE_notes = "CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, lembrete TEXT NOT NULL)"
        private val CREATE_TABLE_Colecao = "CREATE TABLE colecoes(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL)"
        private val CREATE_TABLE_Reminder = "CREATE TABLE reminder(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, nota TEXT NOT NULL, data TEXT NOT NULL, hora TEXT NOT NULL)"
        private val DROP_TABLE_notes = "DROP TABLE notes"
        private val DROP_TABLE_Colecao = "DROP TABLE colecoes"
        private val DROP_TABLE_Reminder = "DROP TABLE reminder"
    }
}