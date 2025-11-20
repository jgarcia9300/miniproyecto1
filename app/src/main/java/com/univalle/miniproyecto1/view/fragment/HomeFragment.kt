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
    // Usamos viewModels() aquí para asociar el ViewModel al Fragment
    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        val recycler = binding.recyclerview
        val layoutManager =LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        val adapter = InventoryAdapter(emptyList(), findNavController()) // Inicializamos con lista vacía
        recycler.adapter = adapter

        adapter.onClickItem = { producto ->
            val bundle = Bundle()
            bundle.putSerializable("dataInventory", producto)
            findNavController().navigate(
                R.id.action_homeFragment_to_itemDetailsFragment,
                bundle
            )
        }

        inventoryViewModel.listInventory.observe(viewLifecycleOwner){ listInventory ->
            adapter.updateList(listInventory)
        }
    }

    private fun observerProgress() {
        inventoryViewModel.progresState.observe(viewLifecycleOwner) { status ->
            binding.progress.isVisible = status
        }
    }
}