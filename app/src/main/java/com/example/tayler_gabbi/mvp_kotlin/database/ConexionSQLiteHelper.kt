package com.example.tayler_gabbi.mvp_kotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log
import android.widget.Toast
import java.util.ArrayList

class ConexionSQLiteHelper{

    val dbNombre="db_mvp"
    val dbVersion=1

    val dbTablaUser="Usuarios"
    val columnaUserID="ID"
    val columnaUsuario="Usuario"
    val columnaContraseña="password"
    val sqlCrearTablaUser ="CREATE TABLE IF NOT EXISTS " + dbTablaUser +" ("+ columnaUserID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            columnaUsuario +" TEXT NOT NULL,"+
            columnaContraseña +" TEXT NOT NULL)"
    ////////////////////////////////////////////////////////////////////////


    var sqlDB:SQLiteDatabase?=null
    constructor(contexto: Context){
        val db=DBHerlperFunciones(contexto)
        sqlDB=db.writableDatabase
    }


    inner class DBHerlperFunciones(contexto: Context):SQLiteOpenHelper(contexto,dbNombre,null,dbVersion){

        var contexto:Context=contexto

        override fun onCreate(db: SQLiteDatabase?) {

            db!!.execSQL(sqlCrearTablaUser)
            Toast.makeText(this.contexto,"la base de datos administrar Dinero a sido creada",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS"+dbTablaUser)
        }
    }



    fun insertarUser(usuario: Usuario):Long{

        val values = ContentValues()
        values.put("usuario", usuario.usuario)
        values.put("contrasenia", usuario.password)

        val ID =sqlDB!!.insert(dbTablaUser,"",values)
        return ID
    }

    fun consultarListaUsuarios(): ArrayList<Usuario> {

        var usuario: Usuario

        val listaUsuarios = ArrayList<Usuario>()
        val cursor = sqlDB!!.rawQuery("SELECT * FROM $dbTablaUser", null)

        while (cursor.moveToNext()) {
            usuario = Usuario()
            usuario.usuId =cursor.getLong(0)
            usuario.usuario = cursor.getString(1)
            usuario.password= cursor.getString(2)
        }
        return listaUsuarios
    }

    fun queryUser(projection:Array<String>,selection:String,selectionArgs:Array<String>,orderBy:String): Cursor {

        val consulta= SQLiteQueryBuilder()
        consulta.tables=dbTablaUser
        val cursor=consulta.query(sqlDB,projection,selection,selectionArgs,null,null,orderBy)
        return cursor
    }
    fun borrarUser(id : String){
        val parametro = arrayOf(id)
        sqlDB!!.delete(dbTablaUser, "$columnaUserID=?", parametro)
    }
    fun actualizarUser(values: ContentValues, selection: String, selectionArgs: Array<String>):Int{
        val contador=sqlDB!!.update(dbTablaUser,values,selection,selectionArgs)
        return contador
    }


    fun actualizarUsuario(usu: Usuario) {

        val parametro = arrayOf<String>(usu.usuId.toString())

        val values = ContentValues()
        values.put(columnaUsuario, usu.usuario)
        values.put(columnaContraseña, usu.password)

        sqlDB!!.update(dbTablaUser, values, "$columnaUserID=?", parametro)

    }

    fun userLogin(usuario:String,contraseña:String):Boolean{
        val query="select * from Usuarios where Usuario = '$usuario' and password = '$contraseña'"
        val cursor=sqlDB!!.rawQuery(query,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true

    }
}