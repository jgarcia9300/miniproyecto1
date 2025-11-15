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
        dataInventory()
        controladores()
    }

    private fun controladores() {

        binding.btnDelete.setOnClickListener {
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

    private fun dataInventory() {
        val receivedBundle = arguments
            ?: throw Exception("ERROR: El Bundle llegó nulo a ItemDetailsFragment")

        val data = receivedBundle.getSerializable("dataInventory")
            ?: throw Exception("ERROR: 'dataInventory' NO existe en el Bundle")

        receivedInventory = data as Inventory

        // 🔥 OBSERVAR LISTA DEL INVENTORY PARA ACTUALIZAR DETALLE AL EDITAR
        inventoryViewModel.listInventory.observe(viewLifecycleOwner) { list ->

            val updatedItem = list.firstOrNull { it.id == receivedInventory.id }

            if (updatedItem != null) {
                receivedInventory = updatedItem  // actualizar objeto

                binding.tvItem.text = updatedItem.name
                binding.tvPrice.text = "$ ${updatedItem.price}"
                binding.tvQuantity.text = updatedItem.quantity.toString()
                binding.txtTotal.text =
                    "$ ${inventoryViewModel.totalProducto(updatedItem.price, updatedItem.quantity)}"
            }
        }
    }


    private fun deleteInventory() {
        inventoryViewModel.deleteInventory(receivedInventory)
        findNavController().popBackStack()
    }
}
