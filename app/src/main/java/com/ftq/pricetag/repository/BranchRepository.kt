package com.ftq.pricetag.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ftq.pricetag.dao.BranchDao
import com.ftq.pricetag.dao.RetailerDao
import com.ftq.pricetag.entity.BranchEntity
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.model.BranchModel
import kotlinx.coroutines.Dispatchers

class BranchRepository(private val branchDao: BranchDao,private val retailerDao: RetailerDao) {
    suspend fun getAllBranches() = branchDao.getAllBranches()
    suspend fun getAllBranchesList() = branchDao.getAllBranchesList()

//    fun getAllRetailers(): LiveData<List<RetailerEntity>> = liveData(Dispatchers.IO) {
//        emit(retailerDao.getAllRetailers())
//    }

    suspend fun getAllRetailers() = retailerDao.getAllRetailers()
     fun getAllRetailersLiveDataList() = retailerDao.getAllRetailersLiveDataList()

//    fun getAllRetailers(): LiveData<List<RetailerEntity>> {
//        return retailerDao.getAllRetailersLiveDataList()
//    }

    fun getAllRetailersList(): List<RetailerEntity>{
        return retailerDao.getAllRetailers()
    }

    suspend fun getBranchesForRetailer(retailerId: Int): List<BranchEntity> {
        return branchDao.getBranchesByRetailerId(retailerId).value ?: listOf()
    }

    suspend fun insert(branch: BranchEntity) {
        branchDao.insert(branch)
    }

    suspend fun update(branch: BranchEntity) {
        branchDao.update(branch)
    }

    suspend fun delete(branch: BranchEntity) {
        branchDao.delete(branch)
    }

    fun getRetailerNameById(retailerId: Int): String {
        val retailer = retailerDao.getRetailerById(retailerId)
        return retailer?.name ?: ""
    }
}
