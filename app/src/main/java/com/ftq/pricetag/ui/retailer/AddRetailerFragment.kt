package com.ftq.pricetag.ui.retailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ftq.pricetag.R
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.databinding.FragmentRetailerAddBinding

class AddRetailerFragment :Fragment() {
    private var _binding:   FragmentRetailerAddBinding ? = null
    private val binding get() = _binding!!
    private val retailerViewModel: RetailerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRetailerAddBinding.inflate(inflater, container, false)

        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString()
            if (name.isNotEmpty()) {
                val retailer = RetailerEntity(id= 0, name = name)
                retailerViewModel.addRetailer(retailer)
                findNavController().navigate(R.id.action_addRetailerFragment_to_retailersFragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}