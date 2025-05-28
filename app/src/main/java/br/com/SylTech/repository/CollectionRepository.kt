package br.com.SylTech.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.SylTech.model.Colecao
import br.com.SylTech.model.Database

class CollectionRepository(context: Context) {
    private val TABLE = "colecoes"
    private val database = Database(context).writableDatabase

    fun Create(colecao: Colecao) : Long {
        val values = ContentValues().apply {
            put("titulo", colecao.titulo)
        }
        return database.insert(TABLE,null,values)
    }

    fun Read() : List<Colecao> {
        val cursor : Cursor = database.rawQuery("select * from $TABLE", null)
        val collection = mutableListOf<Colecao>().apply {
            while(cursor.moveToNext()){
                val collections = Colecao(
                    id = cursor.getLong(0),
                    titulo = cursor.getString(1)
                )
                add(collections)
            }
        }
        cursor.close()
        return collection
    }

    fun Update(colecao: Colecao):Int {
        val values = ContentValues().apply {
            put("titulo", colecao.titulo)
        }
        return database.update(TABLE,values,"id = ?", arrayOf(colecao.id.toString()))
    }

    fun Delete(id:Long):Int {
        return database.delete(TABLE,"id=?", arrayOf(id.toString()))
    }


}