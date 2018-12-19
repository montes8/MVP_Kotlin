package com.example.tayler_gabbi.mvp_kotlin.model

import android.text.TextUtils
import com.example.tayler_gabbi.mvp_kotlin.database.ConexionSQLiteHelper
import com.example.tayler_gabbi.mvp_kotlin.presenter.LoginPresenter
import com.example.tayler_gabbi.mvp_kotlin.view.LoginView

class PresenterImpl(private val loginView : LoginView) : LoginPresenter{

    override fun perfomLogin(userName: String, password: String, conn: ConexionSQLiteHelper) {

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {

            loginView!!.loginValidacion()

        } else {

            if (conn.userLogin(userName, password)) {

                loginView!!.loginSuccess()
            } else {

                loginView!!.loginError()
            }

        }
    }
}