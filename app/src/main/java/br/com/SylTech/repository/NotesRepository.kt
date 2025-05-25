package br.com.SylTech.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.SylTech.model.Database
import br.com.SylTech.model.Notes

class NotesRepository(context: Context) {
    private val TABLE = "notes"
    private val database = Database(context).writableDatabase

    fun Create(notes: Notes) : Long {
        val values = ContentValues().apply {
            put("titulo", notes.title)
            put("lembrete", notes.note)
        }
        return database.insert(TABLE,null,values)
    }

    fun Read() : List<Notes> {
        val cursor : Cursor = database.rawQuery("select * from $TABLE", null)
        val notas = mutableListOf<Notes>().apply {
            while(cursor.moveToNext()){
                val notes = Notes(
                    id = cursor.getLong(0),
                    title = cursor.getString(1),
                    note = cursor.getString(2)
                )
                add(notes)
            }
        }
        cursor.close()
        return notas
    }

    fun Update(notes: Notes):Int {
        val values = ContentValues().apply {
            put("titulo", notes.title)
            put("lembrete", notes.note)
        }
        return database.update(TABLE,values,"id = ?", arrayOf(notes.id.toString()))
    }

    fun Delete(id:Long):Int {
        return database.delete(TABLE,"id=?", arrayOf(id.toString()))
    }


}