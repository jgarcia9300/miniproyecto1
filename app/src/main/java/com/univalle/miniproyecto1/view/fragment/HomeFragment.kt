package com.univalle.miniproyecto1.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.univalle.miniproyecto1.R
import com.univalle.miniproyecto1.databinding.FragmentHomeBinding
import com.univalle.miniproyecto1.view.adapter.InventoryAdapter
import com.univalle.miniproyecto1.viewmodel.InventoryViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controladores()
        observadorViewModel()
    }

    private fun controladores() {
        binding.fbagregar.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addItemFragment)
        }

        binding.contentToolbar.imageToolbarHome.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observadorViewModel() {
        observerListInventory()
        observerProgress()
    }

    private fun observerListInventory() {
        inventoryViewModel.getListInventory()

        inventoryViewModel.listInventory.observe(viewLifecycleOwner) { listInventory ->
            val recycler = binding.recyclerview
            recycler.layoutManager = LinearLayoutManager(context)

            val adapter = InventoryAdapter(listInventory, findNavController())
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()

            // === CLICK EN ITEM PARA ABRIR DETALLES ===
            adapter.onClickItem = { producto ->
                val bundle = Bundle()
                bundle.putSerializable("dataInventory", producto)
                findNavController().navigate(
                    R.id.action_homeFragment_to_itemDetailsFragment,
                    bundle
                )
            }
        }
    }

    private fun observerProgress() {
        inventoryViewModel.progresState.observe(viewLifecycleOwner) { status ->
            binding.progress.isVisible = status
        }
    }
}
