package com.univalle.miniproyecto1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.univalle.miniproyecto1.model.Inventory
import com.univalle.miniproyecto1.repository.InventoryRepository
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application) : AndroidViewModel(application){
    val context = getApplication<Application>()
    private val inventoryRepository = InventoryRepository(context)


    private val _listInventory = MutableLiveData<MutableList<Inventory>>()
    val listInventory: LiveData<MutableList<Inventory>> get() = _listInventory

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState


    fun saveInventory(inventory: Inventory, message: (String) -> Unit) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                inventoryRepository.saveInventory(inventory){ msg ->
                    message(msg)
                }
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }
}