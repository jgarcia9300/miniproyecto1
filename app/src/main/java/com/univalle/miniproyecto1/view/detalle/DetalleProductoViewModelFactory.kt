package com.univalle.miniproyecto1.view.detalle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.univalle.miniproyecto1.data.ProductoRepository

class DetalleProductoViewModelFactory(
    private val repository: ProductoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalleProductoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetalleProductoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}