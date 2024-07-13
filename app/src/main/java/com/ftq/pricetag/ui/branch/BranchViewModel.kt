package com.ftq.pricetag.ui.branch

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ftq.pricetag.AppDatabase
import com.ftq.pricetag.entity.BranchEntity
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.model.BranchModel
import com.ftq.pricetag.repository.BranchRepository
import com.ftq.pricetag.repository.RetailerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BranchViewModel(private val branchRepository: BranchRepository) : ViewModel() {
    private val _branches = MutableLiveData<List<BranchModel>>()
    val branches: LiveData<List<BranchModel>> get() = _branches

    private val _retailers = MutableLiveData<List<RetailerEntity>>()

    val retailers: LiveData<List<RetailerEntity>> = branchRepository.getAllRetailersLiveDataList()

    init {
        loadBranches()
    }

    private fun loadBranches() {
        viewModelScope.launch(Dispatchers.IO) {
            val branchModels = branchRepository.getAllBranchesList()
             branchModels.map { branchModel ->
                Log.d("retailer", branchModel.retailerName )
                Log.d("branch",branchModel.branchName )
            }
            _branches.postValue(branchModels)
        }
    }

    fun addBranch(branch: BranchEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            branchRepository.insert(branch)
            loadBranches()  // Refresh data
        }
    }

    fun deleteBranch(branch: BranchEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            branchRepository.delete(branch)
            loadBranches()  // Refresh data
        }
    }

    fun updateBranch(branch: BranchEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            branchRepository.update(branch)
            loadBranches()  // Refresh data
        }
    }

    fun getRetailerNameById(retailerId: Int): String {
        val retailer = _retailers.value?.find { it.id == retailerId }
        return retailer?.name ?: ""
    }

}
