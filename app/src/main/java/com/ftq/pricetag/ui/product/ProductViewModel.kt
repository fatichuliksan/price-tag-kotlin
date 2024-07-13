package com.ftq.pricetag.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.ftq.pricetag.entity.ProductEntity
import com.ftq.pricetag.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>> get() = _products

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            _products.postValue(repository.getAllProductsList())
        }
    }

    fun addBranch(entity: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(entity)
            load()  // Refresh data
        }
    }

    fun deleteBranch(entity: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(entity)
            load()  // Refresh data
        }
    }

    fun updateBranch(entity: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(entity)
            load()  // Refresh data
        }
    }
}
