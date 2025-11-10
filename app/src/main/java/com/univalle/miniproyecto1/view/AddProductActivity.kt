package com.univalle.miniproyecto1.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.univalle.miniproyecto1.model.Inventory
import com.univalle.miniproyecto1.repository.InventoryRepository
import com.univalle.miniproyecto1.ui.theme.Miniproyecto1Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class AddProductActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repo = InventoryRepository(applicationContext)

        setContent {
            Miniproyecto1Theme {
                val scope = rememberCoroutineScope()

                var code by rememberSaveable { mutableStateOf("") }
                var name by rememberSaveable { mutableStateOf("") }
                var price by rememberSaveable { mutableStateOf("") }
                var qty by rememberSaveable { mutableStateOf("") }

                // Solo dígitos, con tope de longitud
                fun onlyDigits(s: String, max: Int) = s.filter { it.isDigit() }.take(max)

                val allOk =
                    code.isNotBlank() && name.isNotBlank() && price.isNotBlank() && qty.isNotBlank()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Agregar producto", color = Color.White) },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF424242)
                            )
                        )
                    },
                    containerColor = Color(0xFF000000)
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
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

                        // Botón: fondo siempre naranja; texto gris si está deshabilitado, blanco si habilitado
                        val orange = Color(0xFFE7522E)

                        Button(
                            onClick = {
                                val item = Inventory(
                                    id = 0,
                                    code = code,
                                    name = name.trim(),
                                    priceCents = (price.toLongOrNull() ?: 0L) * 100L,
                                    quantity = qty.toIntOrNull() ?: 0
                                )
                                scope.launch {
                                    repo.saveInventory(item)
                                    finish()
                                }
                            },
                            enabled = allOk,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange,
                                contentColor = Color.White,
                                disabledContainerColor = orange,              // fondo igual
                                disabledContentColor = Color(0xFF9E9E9E)     // texto gris
                            )
                        ) {
                            Text(
                                text = "Guardar",
                                fontWeight = if (allOk) FontWeight.Bold else FontWeight.Normal
                                // NO pongas color aquí; lo controla buttonColors
                            )
                        }
                    }
                }
            }
        }
    }
}
