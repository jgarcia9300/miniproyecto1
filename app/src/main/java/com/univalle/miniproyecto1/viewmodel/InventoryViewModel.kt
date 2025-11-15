package com.univalle.miniproyecto1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.univalle.miniproyecto1.model.Inventory
import com.univalle.miniproyecto1.repository.InventoryRepository
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application) : AndroidViewModel(application) {

    private val inventoryRepository = InventoryRepository(application)

    private val _listInventory = MutableLiveData<MutableList<Inventory>>()
    val listInventory: LiveData<MutableList<Inventory>> get() = _listInventory

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> get() = _progresState



    fun saveInventory(inventory: Inventory, message: (String) -> Unit) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                inventoryRepository.saveInventory(inventory) { msg ->
                    message(msg)
                }


                _listInventory.value = inventoryRepository.getListInventory()

            } finally {
                _progresState.value = false
            }
        }
    }


    fun getListInventory() {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _listInventory.value = inventoryRepository.getListInventory()
            } finally {
                _progresState.value = false
            }
        }
    }


    fun deleteInventory(inventory: Inventory) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                inventoryRepository.deleteInventory(inventory)

                // 🔥 REFRESCAR LISTA
                _listInventory.value = inventoryRepository.getListInventory()

            } finally {
                _progresState.value = false
            }
        }
    }



    fun updateInventory(inventory: Inventory) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                inventoryRepository.updateRepositoy(inventory)


                _listInventory.value = inventoryRepository.getListInventory()

            } finally {
                _progresState.value = false
            }
        }
    }


    fun totalProducto(price: Int, quantity: Int): Double {
        return (price * quantity).toDouble()
    }
}
