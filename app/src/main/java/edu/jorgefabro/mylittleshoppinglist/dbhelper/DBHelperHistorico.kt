package edu.jorgefabro.mylittleshoppinglist.dbhelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import edu.jorgefabro.mylittleshoppinglist.models.HistoricoData

//Creador de la base de datos del histórico con la interfaz añadida

interface IDBHelperH {
    fun addHistorico(nombreh: String, cantidadh: String, precioh: String, establecimientosh: String, dia: Int, mes: Int, anyo: Int)
    fun getHistorico(): List<HistoricoData>
}

class DBHelperHistorico(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABSE_NAME, factory, DATABASE_VERSION), IDBHelperH {

    companion object {
        val DATABASE_VERSION = 1
        val DATABSE_NAME = "historico.db"
        val TABLA_HISTORICO = "historico"
        val COLUMNA_ID = "id"
        val COLUMNA_NOMBRE = "nombre"
        val COLUMNA_CANT = "cantidad"
        val COLUMNA_PRECIO = "precio"
        val COLUMNA_ESTABLECIMIENTOS = "establecimientos"
        val COLUMNA_DIA = "día"
        val COLUMNA_MES = "mes"
        val COLUMNA_ANYO = "año"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTableAmigos = "create table $TABLA_HISTORICO" +
                    "($COLUMNA_ID integer primary key autoincrement," +
                    "$COLUMNA_NOMBRE text," +
                    "$COLUMNA_CANT text," +
                    "$COLUMNA_PRECIO text," +
                    "$COLUMNA_ESTABLECIMIENTOS text," +
                    "$COLUMNA_DIA integer," +
                    "$COLUMNA_MES integer," +
                    "$COLUMNA_ANYO integer)"
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

    override fun addHistorico(
        nombreh: String,
        cantidadh: String,
        precioh: String,
        establecimientosh: String,
        dia: Int,
        mes: Int,
        anyo: Int
    ) {
        val data = ContentValues()
        data.put(COLUMNA_NOMBRE, nombreh)
        data.put(COLUMNA_CANT, cantidadh)
        data.put(COLUMNA_PRECIO, precioh)
        data.put(COLUMNA_ESTABLECIMIENTOS, establecimientosh)
        data.put(COLUMNA_DIA, dia)
        data.put(COLUMNA_MES, mes)
        data.put(COLUMNA_ANYO, anyo)
        val db = this.writableDatabase
        db.insert(TABLA_HISTORICO, null, data)
        db.close()
    }

    override fun getHistorico(): List<HistoricoData> {
        val result = ArrayList<HistoricoData>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select $COLUMNA_ID, $COLUMNA_NOMBRE, $COLUMNA_CANT, $COLUMNA_PRECIO, $COLUMNA_ESTABLECIMIENTOS, $COLUMNA_DIA, $COLUMNA_MES, $COLUMNA_ANYO from $TABLA_HISTORICO ",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val idh = cursor.getInt(0)
                val nombreh = cursor.getString(1)
                val cantidadh = cursor.getString(2)
                val precioh = cursor.getString(3)
                val establecimientosh = cursor.getString(4)
                val dia = cursor.getInt(5)
                val mes = cursor.getInt(6)
                val anyo = cursor.getInt(7)
                val historico = HistoricoData(idh, nombreh, cantidadh, precioh, establecimientosh, dia, mes, anyo)
                result.add(historico)
            } while (cursor.moveToNext())
        }

        return result
    }
}