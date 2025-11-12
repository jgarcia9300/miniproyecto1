package com.univalle.miniproyecto1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.univalle.miniproyecto1.databinding.InventoryItemBinding
import com.univalle.miniproyecto1.model.Inventory


class InventoryAdapter (private val listInventory:MutableList<Inventory>, private val navController: NavController):RecyclerView.Adapter<InventoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val binding = ItemInventoryBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return InventoryViewHolder(binding, navController)
    }

    override fun getItemCount(): Int {
        return listInventory.size
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val inventory = listInventory[position]
        holder.setItemInventory(inventory)
    }
}