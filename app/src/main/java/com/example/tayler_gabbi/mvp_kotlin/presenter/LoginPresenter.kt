package com.example.tayler_gabbi.mvp_kotlin.presenter

import com.example.tayler_gabbi.mvp_kotlin.database.ConexionSQLiteHelper

interface LoginPresenter {

    fun perfomLogin( userName : String, password : String , conn : ConexionSQLiteHelper)
}