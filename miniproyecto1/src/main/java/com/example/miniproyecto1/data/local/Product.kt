package com.example.miniproyecto1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,     // Código (4 dígitos)
    val name: String,            // Máx 40 caracteres
    val priceCents: Long,        // Precio en centavos para evitar decimales
    val quantity: Int            // Máx 4 dígitos
)
