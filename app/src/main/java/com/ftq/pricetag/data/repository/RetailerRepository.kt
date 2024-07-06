package com.ftq.pricetag.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ftq.pppb.price_tag.dao.RetailerDao
import com.ftq.pricetag.data.entity.Retailer

class RetailerRepository(private val retailerDao: RetailerDao) {
    fun getAllRetailers(): LiveData<List<Retailer>> = retailerDao.getAllRetailers()


    suspend fun insert(retailer: Retailer) {
        retailerDao.insert(retailer)
    }

    suspend fun update(retailer: Retailer) {
        retailerDao.update(retailer)
    }

    suspend fun delete(retailer: Retailer) {
        retailerDao.delete(retailer)
    }

    fun getRetailerById(id: Long): Retailer? {
        return retailerDao.getRetailerById(id)
    }
}
