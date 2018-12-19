package com.example.tayler_gabbi.mvp_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tayler_gabbi.mvp_kotlin.database.ConexionSQLiteHelper
import com.example.tayler_gabbi.mvp_kotlin.model.PresenterImpl
import com.example.tayler_gabbi.mvp_kotlin.presenter.LoginPresenter
import com.example.tayler_gabbi.mvp_kotlin.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginView {

    private var loginPresenter : LoginPresenter? = null

    var conn: ConexionSQLiteHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = PresenterImpl(this)
        conn = ConexionSQLiteHelper(this)

        button_ingresar.setOnClickListener {
            loginPresenter!!.perfomLogin(edit_usuario.text.toString(),edit_pasword.text.toString(), conn!!)
        }


    }

    override fun loginValidacion() {
        Toast.makeText(this, "Ingrese Usuario y Contraseña", Toast.LENGTH_LONG).show()
    }

    override fun loginSuccess() {

        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun loginError() {
        Toast.makeText(this, "Usuario o congraseña Invalido", Toast.LENGTH_LONG).show()
    }
}
