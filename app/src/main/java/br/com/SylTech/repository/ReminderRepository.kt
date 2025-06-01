package br.com.SylTech.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.SylTech.model.Database
import br.com.SylTech.model.Reminder

class ReminderRepository(context: Context) {
    private val TABLE = "reminder"
    private val database = Database(context).writableDatabase

    fun Create(reminder: Reminder) : Long {
        val values = ContentValues().apply {
            put("titulo", reminder.titulo)
            put("nota", reminder.nota)
            put("data", reminder.data)
            put("hora", reminder.hora)
        }
        return database.insert(TABLE,null,values)
    }

    fun Read() : List<Reminder> {
        val cursor : Cursor = database.rawQuery("select * from $TABLE", null)
        val notas = mutableListOf<Reminder>().apply {
            while(cursor.moveToNext()){
                val notes = Reminder(
                    id = cursor.getLong(0),
                    titulo = cursor.getString(1),
                    nota = cursor.getString(2),
                    data = cursor.getString(3),
                    hora = cursor.getString(4)
                )
                add(notes)
            }
        }
        cursor.close()
        return notas
    }

    fun Update(reminder: Reminder):Int {
        val values = ContentValues().apply {
            put("titulo", reminder.titulo)
            put("nota", reminder.nota)
            put("data", reminder.data)
            put("hora", reminder.hora)
        }
        return database.update(TABLE,values,"id = ?", arrayOf(reminder.id.toString()))
    }

    fun Delete(id:Long):Int {
        return database.delete(TABLE,"id=?", arrayOf(id.toString()))
    }


}