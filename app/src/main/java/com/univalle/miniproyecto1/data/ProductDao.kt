package com.univalle.miniproyecto1.data

import androidx.room.*
import com.univalle.miniproyecto1.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(p: Product)

    @Query("SELECT * FROM products ORDER BY name")
    fun getAll(): Flow<List<Product>>

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteById(id: Int)
}
