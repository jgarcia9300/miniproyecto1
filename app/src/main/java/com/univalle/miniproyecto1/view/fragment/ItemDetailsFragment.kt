package com.univalle.miniproyecto1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.univalle.miniproyecto1.R
import com.univalle.miniproyecto1.databinding.FragmentItemDetailsBinding
import com.univalle.miniproyecto1.model.Inventory
import com.univalle.miniproyecto1.viewmodel.InventoryViewModel
import androidx.fragment.app.activityViewModels

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding
    // Usamos activityViewModels() para compartir el ViewModel con el Fragment de Home/Edición
    private val inventoryViewModel: InventoryViewModel by activityViewModels()

    private lateinit var receivedInventory: Inventory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Cargamos y mostramos los datos del Bundle inmediatamente.
        loadAndDisplayInitialData()

        // 2. Controladores de botones
        controladores()

        // 3. Observador para cambios futuros (ej. después de editar).
        observerInventoryUpdates()
    }

    private fun controladores() {
        binding.btnDelete.setOnClickListener {
            // ... (Lógica de diálogo de eliminación, el código es correcto)
            val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que deseas eliminar este producto?")
                .setNegativeButton("No", null)
                .setPositiveButton("Sí") { _, _ ->
                    deleteInventory()
                }
                .create()
            dialog.show()
        }

        binding.contentToolbar2.toolbarDetailItem.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.fbEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("dataInventory", receivedInventory)
            findNavController().navigate(
                R.id.action_itemDetailsFragment_to_itemEditFragment,
                bundle
            )
        }
    }

    // Función auxiliar para renderizar los datos en la UI
    private fun renderInventoryData(item: Inventory) {
        binding.tvItem.text = item.name
        binding.tvPrice.text = "$ ${item.price}"
        binding.tvQuantity.text = item.quantity.toString()
        binding.txtTotal.text =
            "$ ${inventoryViewModel.totalProducto(item.price, item.quantity)}"
    }

    private fun loadAndDisplayInitialData() {
        val receivedBundle = arguments
            ?: run {
                findNavController().popBackStack() // Volver si no hay datos
                return
            }

        val data = receivedBundle.getSerializable("dataInventory") as? Inventory
            ?: run {
                findNavController().popBackStack() // Volver si el objeto es nulo o incorrecto
                return
            }

        receivedInventory = data

        // Mostrar los datos inmediatamente después de recibirlos
        renderInventoryData(receivedInventory)
    }

    private fun observerInventoryUpdates() {
        inventoryViewModel.listInventory.observe(viewLifecycleOwner) { list ->

            val updatedItem = list.firstOrNull { it.id == receivedInventory.id }

            if (updatedItem != null) {
                receivedInventory = updatedItem // actualizar objeto interno
                // Actualizamos la UI con los datos frescos del LiveData
                renderInventoryData(updatedItem)
            }
        }
    }


    private fun deleteInventory() {
        inventoryViewModel.deleteInventory(receivedInventory)
        findNavController().popBackStack()
    }
}