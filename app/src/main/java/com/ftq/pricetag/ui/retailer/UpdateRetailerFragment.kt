package com.ftq.pricetag.ui.retailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ftq.pricetag.R
import com.ftq.pricetag.data.entity.Retailer
import com.ftq.pricetag.databinding.FragmentRetailerUpdateBinding

class UpdateRetailerFragment : Fragment() {

    private var _binding: FragmentRetailerUpdateBinding? = null
    private val binding get() = _binding!!
    private val retailerViewModel: RetailerViewModel by viewModels()
    private val args by navArgs<UpdateRetailerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRetailerUpdateBinding.inflate(inflater, container, false)

        binding.editTextName.setText(args.retailerName)

        binding.buttonUpdate.setOnClickListener {
            val name = binding.editTextName.text.toString()
            if (name.isNotEmpty()) {
                val retailer = Retailer(id = args.retailerId, name = name)
                retailerViewModel.updateRetailer(retailer)
                findNavController().navigate(R.id.action_updateRetailerFragment_to_retailersFragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}