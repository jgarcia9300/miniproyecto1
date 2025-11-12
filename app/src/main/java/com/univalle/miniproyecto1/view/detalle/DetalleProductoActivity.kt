package com.univalle.miniproyecto1.view.detalle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.univalle.miniproyecto1.R
import com.univalle.miniproyecto1.data.ProductoRepository
import com.univalle.miniproyecto1.databinding.ActivityDetalleProductoBinding

class DetalleProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleProductoBinding
    private val viewModel: DetalleProductoViewModel by viewModels {
        DetalleProductoViewModelFactory(ProductoRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Obtener datos del intent
        val nombre = intent.getStringExtra("nombre") ?: ""
        val precio = intent.getDoubleExtra("precio", 0.0)
        val cantidad = intent.getIntExtra("cantidad", 0)

        // Cargar datos en ViewModel
        viewModel.cargarProducto(nombre, precio, cantidad)

        // Observar cambios
        viewModel.nombre.observe(this) { binding.tvNombre.text = it }
        viewModel.precioUnidad.observe(this) { binding.tvPrecioUnidad.text = "Precio Unidad: $$it" }
        viewModel.cantidad.observe(this) { binding.tvCantidad.text = "Cantidad Disponible: $it" }
        viewModel.total.observe(this) { binding.tvTotal.text = "Total: $$it" }

        // Botón eliminar
        binding.btnEliminar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Eliminar producto")
                .setMessage("¿Seguro que deseas eliminar este producto?")
                .setPositiveButton("Sí") { _, _ ->
                    viewModel.eliminarProducto(nombre)
                    finish() // Regresa a la ventana anterior
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}
