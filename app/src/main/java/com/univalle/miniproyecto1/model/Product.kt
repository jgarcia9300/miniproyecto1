package com.univalle.miniproyecto1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val priceCents: Long,
    val quantity: Int
)
