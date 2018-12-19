package com.example.tayler_gabbi.mvp_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tayler_gabbi.mvp_kotlin.database.ConexionSQLiteHelper
import com.example.tayler_gabbi.mvp_kotlin.model.RegistroPresenterImpl
import com.example.tayler_gabbi.mvp_kotlin.presenter.RegistroPresenter
import com.example.tayler_gabbi.mvp_kotlin.view.RegistroView
import android.content.Intent
import kotlinx.android.synthetic.main.activity_registro.*


class RegistroActivity : AppCompatActivity(),RegistroView {

    private var registrarPresenter: RegistroPresenter? = null
    var conn: ConexionSQLiteHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        registrarPresenter = RegistroPresenterImpl(this)

        conn = ConexionSQLiteHelper(this)

        button_register_ingresar.setOnClickListener {
            registrarPresenter!!.registrarUsuario(edit_text_name.text.toString(),edit_text_usuario.text.toString(),edit_text_password.text.toString(),conn!!)
        }
    }

    override fun registrarSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "usuario registrado", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun nameError() {
        Toast.makeText(this,"Ingrese nombre",Toast.LENGTH_LONG).show();
    }

    override fun usuarioError() {
        Toast.makeText(this,"Ingrese nombre de usuario",Toast.LENGTH_LONG).show();
    }

    override fun passwordError() {
        Toast.makeText(this,"Ingrese  Contraseña",Toast.LENGTH_LONG).show();
    }

    override fun registerError() {

        Toast.makeText(this,"usuario no guardado",Toast.LENGTH_LONG).show();

    }
}
