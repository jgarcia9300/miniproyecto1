package com.univalle.miniproyecto1.data

import androidx.room.*
import com.univalle.miniproyecto1.model.Inventory

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInventory(item: Inventory)

    @Query("SELECT * FROM inventory ORDER BY id DESC")
    suspend fun getListInventory(): List<Inventory>

    @Delete
    suspend fun deleteInventory(item: Inventory)

    @Update
    suspend fun updateInventory(item: Inventory)
}
