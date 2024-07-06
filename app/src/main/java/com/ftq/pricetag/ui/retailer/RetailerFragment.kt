package com.ftq.pricetag.ui.retailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ftq.pricetag.R
import com.ftq.pricetag.data.AppDatabase
import com.ftq.pricetag.databinding.FragmentProductBinding
import com.ftq.pricetag.databinding.FragmentRetailerBinding
import com.ftq.pricetag.repository.RetailerRepository

class RetailerFragment : Fragment() {
    private var _binding: FragmentRetailerBinding? = null
    private val binding get() = _binding!!
    private val retailerViewModel: RetailerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRetailerBinding.inflate(inflater, container, false)


        val adapter = RetailerAdapter()
        binding.recyclerViewRetailers.adapter = adapter
        binding.recyclerViewRetailers.layoutManager = LinearLayoutManager(requireContext())

        retailerViewModel.allRetailers.observe(
            viewLifecycleOwner
        ) { retailerViewModel ->
            retailerViewModel?.let {
                adapter.setRetailers(it)
            }
        }

        binding.fabAddRetailer.setOnClickListener{
            findNavController().navigate(R.id.action_retailersFragment_to_addRetailerFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}