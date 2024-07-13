package com.ftq.pricetag.repository

import androidx.lifecycle.LiveData
import com.ftq.pricetag.dao.RetailerDao
import com.ftq.pricetag.entity.RetailerEntity

class RetailerRepository(private val retailerDao: RetailerDao) {
    fun getAllRetailers(): LiveData<List<RetailerEntity>> = retailerDao.getAllRetailersLiveDataList()
      fun getAllRetailers2(): List<RetailerEntity> = retailerDao.getAllRetailers2()

    suspend fun insert(retailer: RetailerEntity) {
        retailerDao.insert(retailer)
    }

    suspend fun update(retailer: RetailerEntity) {
        retailerDao.update(retailer)
    }

    suspend fun delete(retailer: RetailerEntity) {
        retailerDao.delete(retailer)
    }

    fun getRetailerById(id: Int): RetailerEntity? {
        return retailerDao.getRetailerById(id)
    }
}
