package com.example.miniproyecto1.data

import com.example.miniproyecto1.data.local.Product
import com.example.miniproyecto1.data.local.ProductDao

class InventoryRepository(private val dao: ProductDao) {
    suspend fun insert(p: Product) = dao.insert(p)
    fun all() = dao.getAll()
}