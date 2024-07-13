package com.ftq.pricetag.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ftq.pricetag.dao.BranchDao
import com.ftq.pricetag.dao.ProductDao
import com.ftq.pricetag.dao.RetailerDao
import com.ftq.pricetag.entity.BranchEntity
import com.ftq.pricetag.entity.ProductEntity
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.model.BranchModel
import kotlinx.coroutines.Dispatchers

class ProductRepository(private val productDao: ProductDao) {

    suspend fun insert(entity: ProductEntity) {
        productDao.insert(entity)
    }

    suspend fun update(entity: ProductEntity) {
        productDao.update(entity)
    }

    suspend fun delete(entity: ProductEntity) {
        productDao.delete(entity)
    }

//    suspend fun getAllProudcts() = productDao.getAllProducts()
    suspend fun getAllProductsList() = productDao.getAllProductsList()

}
