package edu.jorgefabro.mylittleshoppinglist.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import edu.jorgefabro.mylittleshoppinglist.models.ProductosData
import kotlinx.coroutines.selects.select

    //Creador de la base de datos de los productos con la interfaz añadida

interface IDBHelperP {
    fun addProducto(
        nombre: String,
        cantidad: String,
        precio: String,
        establecimientos: String/*, select: String*/
    )

    fun delProducto(id: Int): Int
    fun getTodosProductos(): List<ProductosData>
    /*fun getUnProducto(id: Int): ProductosData?
    fun updateProductos(id: Int, nombre: String, cantidad: String, precio: String): Int*/
}

class DBHelperProductos(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABSE_NAME, factory, DATABASE_VERSION), IDBHelperP {

    companion object {
        val DATABASE_VERSION = 1
        val DATABSE_NAME = "productos.db"
        val TABLA_PRODUCTOS = "productos"
        val COLUMNA_ID = "id"
        val COLUMNA_NOMBRE = "nombre"
        val COLUMNA_CANT = "cantidad"
        val COLUMNA_PRECIO = "precio"

        //val COLUMNA_SELECT = "seleccionado"
        val COLUMNA_ESTABLECIMIENTOS = "establecimientos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTableAmigos = "create table $TABLA_PRODUCTOS" +
                    "($COLUMNA_ID integer primary key autoincrement," +
                    "$COLUMNA_NOMBRE text," +
                    "$COLUMNA_CANT text," +
                    "$COLUMNA_PRECIO text," +
                    "$COLUMNA_ESTABLECIMIENTOS text)"
            //"$COLUMNA_SELECT integer)"
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

    override fun addProducto(
        nombre: String,
        cantidad: String,
        precio: String,
        establecimientos: String/*, select: String*/
    ) {
        val data = ContentValues()
        data.put(COLUMNA_NOMBRE, nombre)
        data.put(COLUMNA_CANT, cantidad)
        data.put(COLUMNA_PRECIO, precio)
        data.put(COLUMNA_ESTABLECIMIENTOS, establecimientos)
        //data.put(COLUMNA_SELECT, select)
        val db = this.writableDatabase
        db.insert(TABLA_PRODUCTOS, null, data)
        db.close()
    }

    override fun delProducto(id: Int): Int {
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val result = db.delete(TABLA_PRODUCTOS, "$COLUMNA_ID=?", args)
        db.close()
        return result
    }

    override fun getTodosProductos(): List<ProductosData> {
        val result = ArrayList<ProductosData>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select * from $TABLA_PRODUCTOS ",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val cantidad = cursor.getString(2)
                val precio = cursor.getString(3)
                val establecimientos = cursor.getString(4)
                //val select = cursor.getInt(4) > 0
                val producto =
                    ProductosData(id, nombre, cantidad, precio, establecimientos/*, select*/)
                result.add(producto)
            } while (cursor.moveToNext())
        }

        return result
    }

    /*override fun getUnProducto(id: Int): ProductosData? {
        var productos: ProductosData? = null
        val args = arrayOf(id.toString())
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select $COLUMNA_ID, $COLUMNA_NOMBRE, $COLUMNA_CANT from $TABLA_PRODUCTOS where $COLUMNA_ID = ?",
            args
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val cantidad = cursor.getInt(2)
                val precio = cursor.getInt(3)
                productos = ProductosData(id, nombre, cantidad, precio)
            } while (cursor.moveToNext())
        }

        return productos
    }*/

    /*override fun updateProductos(id: Int, nombre: String, cantidad: String, precio: String): Int {
        val args = arrayOf(id.toString())
        val db = this.writableDatabase
        val data = ContentValues()
        data.put(COLUMNA_NOMBRE, nombre)
        data.put(COLUMNA_CANT, cantidad)
        data.put(COLUMNA_PRECIO, precio)
        val result = db.update(TABLA_PRODUCTOS, data, "$COLUMNA_ID = ?", args)
        db.close()
        return result
    }*/

}