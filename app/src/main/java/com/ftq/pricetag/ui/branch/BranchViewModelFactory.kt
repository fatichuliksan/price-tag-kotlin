package com.ftq.pricetag.ui.branch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ftq.pricetag.repository.BranchRepository
import com.ftq.pricetag.repository.RetailerRepository

class BranchViewModelFactory(private val branchRepository: BranchRepository ) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BranchViewModel::class.java)) {
//            return BranchViewModel(branchRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")

        return if (modelClass.isAssignableFrom(BranchViewModel::class.java)) {
            BranchViewModel(branchRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}