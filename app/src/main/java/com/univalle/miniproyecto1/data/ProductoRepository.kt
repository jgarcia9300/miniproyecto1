package com.univalle.miniproyecto1.data

import android.content.Context

class ProductoRepository(private val context: Context) {

    fun eliminarProducto(nombre: String) {
        // Aquí más adelante puedes implementar la eliminación real
        // por ejemplo, si usas SQLite o Room.
        // De momento, puedes dejarlo vacío o imprimir un log.
        println("Producto eliminado: $nombre")
    }
}
