package com.example.miniproyecto1

import android.app.Application
import androidx.room.Room
import com.example.miniproyecto1.data.InventoryRepository
import com.example.miniproyecto1.data.local.AppDatabase

class App : Application() {
    lateinit var db: AppDatabase
        private set
    lateinit var repo: InventoryRepository
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "inventory.db").build()
        repo = InventoryRepository(db.productDao())
    }
}
