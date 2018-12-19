package com.example.tayler_gabbi.mvp_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tayler_gabbi.mvp_kotlin.view.LoginView

class LoginActivity : AppCompatActivity(),LoginView {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }



    
}
