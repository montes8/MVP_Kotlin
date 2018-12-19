package com.example.tayler_gabbi.mvp_kotlin.model

import com.example.tayler_gabbi.mvp_kotlin.database.Usuario
import android.text.TextUtils
import com.example.tayler_gabbi.mvp_kotlin.database.ConexionSQLiteHelper
import com.example.tayler_gabbi.mvp_kotlin.presenter.RegistroPresenter
import com.example.tayler_gabbi.mvp_kotlin.view.RegistroView


class RegistroPresenterImpl(private val registrarView: RegistroView) : RegistroPresenter{

  override fun
     registrarUsuario(name: String, userName: String, password: String, conn: ConexionSQLiteHelper) {

        if (TextUtils.isEmpty(name)) {

            registrarView.nameError()

        } else if (TextUtils.isEmpty(userName)) {

            registrarView.usuarioError()
        } else if (TextUtils.isEmpty(password)) {

            registrarView.passwordError()

        } else {

            val usuarioi = Usuario()
            usuarioi.usuario = userName
            usuarioi.password = password
            val idResultante = conn.insertarUser(usuarioi)

            if (idResultante != null && idResultante > 0) {

                registrarView.registrarSuccess()

            } else {

                registrarView.registerError()
            }


        }
    }
}