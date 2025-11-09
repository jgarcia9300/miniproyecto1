package com.example.miniproyecto1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.miniproyecto1.ui.add.AddProductActivity
import com.example.miniproyecto1.ui.theme.Miniproyecto1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Miniproyecto1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        // FAB para abrir la HU4 (Agregar producto)
                        FloatingActionButton(
                            onClick = {
                                startActivity(Intent(this, AddProductActivity::class.java))
                            }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Agregar producto")
                        }
                    }
                ) { inner ->
                    HomeScreen(modifier = Modifier.padding(inner))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Contenido temporal del Home (HU3 irá aquí luego)
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Home Inventario",
            style = MaterialTheme.typography.titleLarge
        )
    }
}
