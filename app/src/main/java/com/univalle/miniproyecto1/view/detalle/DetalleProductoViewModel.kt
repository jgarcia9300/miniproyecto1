package com.univalle.miniproyecto1.view.detalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.univalle.miniproyecto1.data.ProductoRepository

class DetalleProductoViewModel(
    private val repository: ProductoRepository
) : ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _precioUnidad = MutableLiveData<Double>()
    val precioUnidad: LiveData<Double> = _precioUnidad

    private val _cantidad = MutableLiveData<Int>()
    val cantidad: LiveData<Int> = _cantidad

    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double> = _total

    fun cargarProducto(nombre: String, precio: Double, cantidad: Int) {
        _nombre.value = nombre
        _precioUnidad.value = precio
        _cantidad.value = cantidad
        _total.value = precio * cantidad
    }

    fun eliminarProducto(nombre: String) {
        repository.eliminarProducto(nombre)
    }
}
