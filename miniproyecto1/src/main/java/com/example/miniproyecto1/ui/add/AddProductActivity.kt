package com.example.miniproyecto1.ui.add

import androidx.compose.material3.ExperimentalMaterial3Api
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.miniproyecto1.App
import com.example.miniproyecto1.data.local.Product
import kotlinx.coroutines.launch

// 👇 Import correcto para KeyboardOptions (con alias para evitar choques)
import androidx.compose.foundation.text.KeyboardOptions as KbOptions

@OptIn(ExperimentalMaterial3Api::class)
class AddProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repo = (application as App).repo

        setContent {
            val scope = rememberCoroutineScope()

            var code by rememberSaveable { mutableStateOf("") }
            var name by rememberSaveable { mutableStateOf("") }
            var price by rememberSaveable { mutableStateOf("") }
            var qty by rememberSaveable { mutableStateOf("") }

            fun onlyDigits(s: String, max: Int) = s.filter { it.isDigit() }.take(max)
            val allOk = code.isNotBlank() && name.isNotBlank() && price.isNotBlank() && qty.isNotBlank()

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Agregar producto", color = Color.White) },
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF424242))
                    )
                },
                containerColor = Color(0xCC000000)
            ) { padding ->
                Column(
                    Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = code,
                        onValueChange = { code = onlyDigits(it, 4) },
                        label = { Text("Código producto") },
                        keyboardOptions = KbOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it.take(40) },
                        label = { Text("Nombre artículo") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = onlyDigits(it, 20) },
                        label = { Text("Precio") },
                        keyboardOptions = KbOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = qty,
                        onValueChange = { qty = onlyDigits(it, 4) },
                        label = { Text("Cantidad") },
                        keyboardOptions = KbOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val p = Product(
                                id = code.toInt(),
                                name = name.trim(),
                                priceCents = price.toLong() * 100,
                                quantity = qty.toInt()
                            )
                            scope.launch {
                                repo.insert(p)  // ajusta si tu repo usa otro nombre de método
                                finish()
                            }
                        },
                        enabled = allOk,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7522E)),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Guardar",
                            color = Color.White,
                            fontWeight = if (allOk) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}
