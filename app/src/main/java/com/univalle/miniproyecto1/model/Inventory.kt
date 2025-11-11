package com.univalle.miniproyecto1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "inventory_table")
data class Inventory(
    // id como llave primariay autoincrementable
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // resto de campos de la tabla
    val code: Int,
    val name: String,
    val price: Int,
    val quantity: Int): Serializable

