package com.univalle.miniproyecto1.view.detalle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.appbar.MaterialToolbar
import com.univalle.miniproyecto1.data.ProductoRepository
import com.univalle.miniproyecto1.databinding.FragmentDetalleProductoBinding
import com.univalle.miniproyecto1.R

class DetalleProductoFragment : Fragment() {

    private var _binding: FragmentDetalleProductoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetalleProductoViewModel by viewModels {
        DetalleProductoViewModelFactory(ProductoRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleProductoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Recuperar los argumentos
        val nombre = arguments?.getString("nombre") ?: ""
        val precio = arguments?.getDouble("precio") ?: 0.0
        val cantidad = arguments?.getInt("cantidad") ?: 0

        // Cargar datos en el ViewModel
        viewModel.cargarProducto(nombre, precio, cantidad)

        // Observadores del ViewModel
        viewModel.nombre.observe(viewLifecycleOwner) {
            binding.tvNombre.text = it
        }
        viewModel.precioUnidad.observe(viewLifecycleOwner) {
            binding.tvPrecioUnidad.text = "Precio Unidad: $${"%.2f".format(it)}"
        }
        viewModel.cantidad.observe(viewLifecycleOwner) {
            binding.tvCantidad.text = "Cantidad Disponible: $it"
        }
        viewModel.total.observe(viewLifecycleOwner) {
            binding.tvTotal.text = "Total: $${"%.2f".format(it)}"
        }

        // Acción del botón Editar (temporal)
        binding.fabEditar.setOnClickListener {
            val nombreProd = viewModel.nombre.value ?: ""
            val precioProd = viewModel.precioUnidad.value ?: 0.0
            val cantidadProd = viewModel.cantidad.value ?: 0

            // Mensaje temporal (hasta que el fragmento de edición exista)
            AlertDialog.Builder(requireContext())
                .setTitle("Editar producto")
                .setMessage(
                    "Aquí se abriría la pantalla de edición.\n\n" +
                            "Datos a enviar:\n" +
                            "Nombre: $nombreProd\n" +
                            "Precio: $precioProd\n" +
                            "Cantidad: $cantidadProd"
                )
                .setPositiveButton("OK", null)
                .show()


            /*
            val bundle = Bundle().apply {
                putString("nombre", nombreProd)
                putDouble("precio", precioProd)
                putInt("cantidad", cantidadProd)
            }

            val editarFragment = EditarProductoFragment().apply {
                arguments = bundle
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.navigationContainer, editarFragment)
                .addToBackStack(null)
                .commit()
            */
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
