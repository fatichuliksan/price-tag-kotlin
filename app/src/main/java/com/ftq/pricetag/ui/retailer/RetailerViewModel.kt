package com.ftq.pricetag.ui.retailer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ftq.pricetag.AppDatabase
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.repository.RetailerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RetailerViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: RetailerRepository
    val allRetailers: LiveData<List<RetailerEntity>>

    init {
        val retailerDao = AppDatabase.getDatabase(application).retailerDao()
        repository = RetailerRepository(retailerDao)
        allRetailers = repository.getAllRetailers()
    }

    fun addRetailer(retailer: RetailerEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(retailer)
        }
    }

    fun updateRetailer(retailer: RetailerEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(retailer)
        }
    }

    fun deleteRetailer(retailer: RetailerEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(retailer)
        }
    }
}