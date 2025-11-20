package com.univalle.miniproyecto1.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.univalle.miniproyecto1.R
import com.univalle.miniproyecto1.view.fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.getStringExtra("open_fragment") == "login") {
            supportFragmentManager.beginTransaction()
                .replace(R.id.navigationContainer, LoginFragment())
                .commit()
        }
    }}
