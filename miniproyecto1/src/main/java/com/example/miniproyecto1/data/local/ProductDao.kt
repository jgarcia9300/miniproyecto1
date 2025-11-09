package com.example.miniproyecto1.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(p: Product)

    @Query("SELECT * FROM products ORDER BY name")
    fun getAll(): Flow<List<Product>>
}
