package com.univalle.miniproyecto1.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.univalle.miniproyecto1.ui.theme.Miniproyecto1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Miniproyecto1Theme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Inventario") }) }
                ) { padding ->
                    Column(Modifier.padding(padding).padding(16.dp)) {
                        Button(
                            onClick = {
                                startActivity(
                                    Intent(this@MainActivity, AddProductActivity::class.java)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) { Text("Agregar producto") }
                    }
                }
            }
        }
    }
}
