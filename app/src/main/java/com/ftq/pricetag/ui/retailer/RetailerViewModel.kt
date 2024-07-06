package com.ftq.pricetag.ui.retailer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftq.pricetag.data.AppDatabase
import com.ftq.pricetag.data.entity.Retailer
import com.ftq.pricetag.repository.RetailerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RetailerViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: RetailerRepository
    val allRetailers: LiveData<List<Retailer>>

    init {
        val retailerDao = AppDatabase.getDatabase(application).retailerDao()
        repository = RetailerRepository(retailerDao)
        allRetailers = repository.getAllRetailers()
    }

    fun addRetailer(retailer: Retailer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(retailer)
        }
    }

    fun updateRetailer(retailer: Retailer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(retailer)
        }
    }

    fun deleteRetailer(retailer: Retailer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(retailer)
        }
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is retailer Fragment"
    }
    val text: LiveData<String> = _text
}