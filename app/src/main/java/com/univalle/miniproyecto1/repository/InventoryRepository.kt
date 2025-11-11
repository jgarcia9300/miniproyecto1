package com.univalle.miniproyecto1.repository

import android.content.Context
import com.univalle.miniproyecto1.data.InventoryDB
import com.univalle.miniproyecto1.model.Inventory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventoryRepository(context: Context) {
    private val inventoryDao = InventoryDB.getDatabase(context).inventoryDao()

    suspend fun saveInventory(inventory: Inventory) = withContext(Dispatchers.IO) {
        inventoryDao.saveInventory(inventory)
    }

    // <-- cambia MutableList por List
    suspend fun getListInventory(): List<Inventory> = withContext(Dispatchers.IO) {
        inventoryDao.getListInventory()
    }

    suspend fun deleteInventory(inventory: Inventory) = withContext(Dispatchers.IO) {
        inventoryDao.deleteInventory(inventory)
    }

    suspend fun updateInventory(inventory: Inventory) = withContext(Dispatchers.IO) {
        inventoryDao.updateInventory(inventory)
    }
}
