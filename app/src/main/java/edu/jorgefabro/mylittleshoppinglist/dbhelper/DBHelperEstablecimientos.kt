package edu.jorgefabro.mylittleshoppinglist.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import edu.jorgefabro.mylittleshoppinglist.models.EstablecimientosData

    //Creador de la base de datos de los establecimientos con la interfaz añadida

interface IDBHelperE {
    fun addEstablecimientos(name: String, direccion: String, telf: String, url: String)
    fun delEstablecimientos(id: Int): Int
    fun getTodoEstablecimientos(): List<EstablecimientosData>
    fun getEstablecimientosSpinner(): List<String>
    /*fun getUnEstablecimiento(id: Int): EstablecimientosData?
    fun updateEstablecimiento(id: Int, nombre: String, direccion: String, telf: String, url: String): Int*/
}

class DBHelperEstablecimientos(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABSE_NAME, factory, DATABASE_VERSION), IDBHelperE {

    companion object {
        val DATABASE_VERSION = 1
        val DATABSE_NAME = "establecimientos.db"
        val TABLA_ESTAB = "establecimientos"
        val COLUMNA_ID = "id"
        val COLUMNA_NOMBRE = "nombre"
        val COLUMNA_DIRECCION = "dirección"
        val COLUMNA_TELF = "teléfono"
        val COLUMNA_URL = "url"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTableAmigos = "create table $TABLA_ESTAB" +
                    "($COLUMNA_ID integer primary key autoincrement," +
                    "$COLUMNA_NOMBRE text," +
                    "$COLUMNA_DIRECCION text," +
                    "$COLUMNA_TELF text," +
                    "$COLUMNA_URL text)"
            db!!.execSQL(createTableAmigos)
        } catch (_: java.lang.Exception) {
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    override fun addEstablecimientos(nombre: String, direccion: String, telf: String, url: String) {
        val data = ContentValues()
        data.put(COLUMNA_NOMBRE, nombre)
        data.put(COLUMNA_DIRECCION, direccion)
        data.put(COLUMNA_TELF, telf)
        data.put(COLUMNA_URL, url)
        val db = this.writableDatabase
        db.insert(TABLA_ESTAB, null, data)
        db.close()
    }

    override fun delEstablecimientos(id: Int): Int {
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val result = db.delete(TABLA_ESTAB, "$COLUMNA_ID=?", args)
        db.close()
        return result
    }

    override fun getTodoEstablecimientos(): List<EstablecimientosData> {
        val result = ArrayList<EstablecimientosData>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select $COLUMNA_ID, $COLUMNA_NOMBRE, $COLUMNA_DIRECCION, $COLUMNA_TELF, $COLUMNA_URL from $TABLA_ESTAB ",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val direccion = cursor.getString(2)
                val telf = cursor.getString(3)
                val url = cursor.getString(4)
                val establecimientos = EstablecimientosData(id, nombre, direccion, telf, url)
                result.add(establecimientos)
            } while (cursor.moveToNext())
        }

        return result
    }

    override fun getEstablecimientosSpinner(): List<String> {
        val result = ArrayList<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select $COLUMNA_NOMBRE from $TABLA_ESTAB ",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(0)
                result.add(nombre)
            } while (cursor.moveToNext())
        }

        return result
    }


    /*override fun getUnEstablecimiento(id: Int): DataFilesEstable? {
        var establecimientos: DataFilesEstable? = null
        val args = arrayOf(id.toString())
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select $COLUMNA_ID, $COLUMNA_NOMBRE, $COLUMNA_DIRECCION, $COLUMNA_TELF, $COLUMNA_URL from $TABLA_ESTAB where $COLUMNA_ID = ?",
            args
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val direccion = cursor.getString(2)
                val telf = cursor.getString(3)
                val url = cursor.getString(4)
                establecimientos = DataFilesEstable(id, nombre, direccion, telf, url)
            } while (cursor.moveToNext())
        }

        return establecimientos
    }

    override fun updateEstablecimiento(id: Int, nombre: String, direccion: String, telf: String, url: String): Int{
        val args = arrayOf(id.toString())
        val db = this.writableDatabase
        val data = ContentValues()
        data.put(COLUMNA_NOMBRE, nombre)
        data.put(COLUMNA_DIRECCION, direccion)
        data.put(COLUMNA_TELF, telf)
        data.put(COLUMNA_URL, url)
        val result = db.update(TABLA_ESTAB, data, "$COLUMNA_ID = ?", args)
        db.close()
        return result
    }*/

}