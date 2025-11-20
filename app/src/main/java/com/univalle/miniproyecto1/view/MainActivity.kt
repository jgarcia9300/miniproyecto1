package com.univalle.miniproyecto1.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.univalle.miniproyecto1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.navigationContainer) as NavHostFragment
        val navController = navHost.navController

        if (intent.getStringExtra("open_fragment") == "login") {
            navController.navigate(R.id.loginFragment)
        }
    }
}

