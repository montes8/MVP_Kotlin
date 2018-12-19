package com.example.tayler_gabbi.mvp_kotlin.presenter

import com.example.tayler_gabbi.mvp_kotlin.database.ConexionSQLiteHelper



interface RegistroPresenter {


    fun registrarUsuario(name: String, userName: String, password: String, conn: ConexionSQLiteHelper)
}