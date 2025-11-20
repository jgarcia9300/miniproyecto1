package com.univalle.miniproyecto1.view.viewholder

import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.univalle.miniproyecto1.R
import com.univalle.miniproyecto1.databinding.ItemInventoryBinding
import com.univalle.miniproyecto1.model.Inventory

class InventoryViewHolder(private val binding: ItemInventoryBinding, private val navController: NavController) :
    RecyclerView.ViewHolder(binding.root) {

    fun setItemInventory(inventory: Inventory) {

        binding.tvName.text = inventory.name
        binding.tvPrice.text = "$ ${inventory.price}"
        binding.tvQuantity.text = "${inventory.quantity}"

        binding.cvInventory.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("dataInventory", inventory)
            }
            navController.navigate(R.id.action_homeFragment_to_itemDetailsFragment, bundle)
        }
    }
}
