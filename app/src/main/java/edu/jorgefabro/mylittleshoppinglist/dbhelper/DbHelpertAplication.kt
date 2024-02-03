package edu.jorgefabro.mylittleshoppinglist.dbhelper

import android.app.Application

class DbHelpertAplication : Application() {

    companion object {
        lateinit var dataSourceE: IDBHelperE
        lateinit var dataSourceP: IDBHelperP
        lateinit var dataSourceH: IDBHelperH
    }

    override fun onCreate() {
        super.onCreate()
        dataSourceE = DBHelperEstablecimientos(applicationContext, null)
        dataSourceP = DBHelperProductos(applicationContext, null)
        dataSourceH = DBHelperHistorico(applicationContext, null)
    }
}