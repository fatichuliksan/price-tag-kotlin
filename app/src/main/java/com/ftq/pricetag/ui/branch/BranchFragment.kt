package com.ftq.pricetag.ui.branch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ftq.pricetag.AppDatabase
import com.ftq.pricetag.R
import com.ftq.pricetag.databinding.FragmentBranchBinding
import com.ftq.pricetag.entity.BranchEntity
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.model.BranchModel
import com.ftq.pricetag.repository.BranchRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BranchFragment : Fragment() {

    private lateinit var binding: FragmentBranchBinding
    private lateinit var branchViewModel: BranchViewModel
    private lateinit var branchAdapter: BranchAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBranchBinding.inflate(inflater, container, false)
        val branchDao = AppDatabase.getDatabase(requireContext()).branchDao()
        val retailerDao = AppDatabase.getDatabase(requireContext()).retailerDao()

        val branchRepository = BranchRepository(branchDao, retailerDao)

        branchViewModel = ViewModelProvider(this, BranchViewModelFactory(branchRepository)).get(BranchViewModel::class.java)
        binding.viewModel = branchViewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        setupObservers()

        binding.buttonAddBranch.setOnClickListener {
            showBranchDialog(null)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        branchAdapter = BranchAdapter(
            listOf(),
            onEditClick = { branch ->
                showBranchDialog(branch)
            },
            onDeleteClick = { branch ->
                branchViewModel.deleteBranch(BranchEntity(branch.id, branch.retailerId, branch.branchName))
            }
        )

        binding.recyclerViewBranch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = branchAdapter
        }
    }

    private fun setupObservers() {
        branchViewModel.branches.observe(viewLifecycleOwner, Observer { branches ->
            branchAdapter.updateData(branches)
        })

        branchViewModel.retailers.observe(viewLifecycleOwner, Observer { retailers ->
            // Update UI or handle retailer data
            updateRetailerSpinner(retailers)
        })
    }

    private fun updateRetailerSpinner(retailers: List<RetailerEntity>) {
        val retailerNames = retailers.map { it.name }
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, retailerNames)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRetailer.adapter = spinnerAdapter
    }

    private fun showBranchDialog(branch: BranchModel?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_branch, null)
        val spinnerRetailer = dialogView.findViewById<Spinner>(R.id.spinnerRetailer)
        val branchNameInput = dialogView.findViewById<EditText>(R.id.editTextBranchName)

//        // Fill spinner with retailers
        val retailers = branchViewModel.retailers.value ?: listOf()
        retailers.map {
            Log.d("retailers", it.name)
        }

        branchViewModel.retailers.observe(viewLifecycleOwner, Observer { retailers ->
            val retailerNames = retailers.map { it.name }
            val spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, retailerNames)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerRetailer.adapter = spinnerAdapter

            if (branch != null) {
                val retailerName = branch.retailerName
                spinnerRetailer.setSelection(retailerNames.indexOf(retailerName))
                branchNameInput.setText(branch.branchName)
            }
        })

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(if (branch == null) "Add Branch" else "Edit Branch")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val selectedRetailerName = spinnerRetailer.selectedItem as String
                val retailerId = getRetailerIdByName(selectedRetailerName)
                val branchName = branchNameInput.text.toString()

                if (branch == null) {
                    branchViewModel.addBranch(BranchEntity(0, retailerId, branchName))
                } else {
                    branchViewModel.updateBranch(BranchEntity(branch.id, retailerId, branchName))
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun getRetailerIdByName(retailerName: String): Int {
        val retailer = branchViewModel.retailers.value?.find { it.name == retailerName }
        return retailer?.id ?: 0
    }
}
