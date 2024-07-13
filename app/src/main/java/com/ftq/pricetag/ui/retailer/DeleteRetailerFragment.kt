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
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.databinding.FragmentRetailerDeleteBinding

class DeleteRetailerFragment : Fragment() {

    private var _binding: FragmentRetailerDeleteBinding? = null
    private val binding get() = _binding!!
    private val retailerViewModel: RetailerViewModel by viewModels()
    private val args by navArgs<DeleteRetailerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRetailerDeleteBinding.inflate(inflater, container, false)

        binding.buttonDelete.setOnClickListener {
            val retailer = RetailerEntity(id = args.retailerId, name = "")
            retailerViewModel.deleteRetailer(retailer)
            findNavController().navigate(R.id.action_deleteRetailerFragment_to_retailersFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
