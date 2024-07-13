package com.ftq.pricetag.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ftq.pricetag.entity.BranchEntity
import com.ftq.pricetag.model.BranchModel

@Dao
interface BranchDao {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insert(branch: BranchEntity)

    @Update
    fun update(branch: BranchEntity)

    @Delete
    fun delete(branch: BranchEntity)

    @Query("SELECT branches.id, branches.retailerId, branches.name AS branchName, retailers.name AS retailerName FROM branches INNER JOIN retailers ON branches.retailerId = retailers.id")
    fun getAllBranches(): LiveData<List<BranchModel>>

    @Query("SELECT branches.id, branches.retailerId, branches.name AS branchName, retailers.name AS retailerName FROM branches INNER JOIN retailers ON branches.retailerId = retailers.id")
    fun getAllBranchesList(): List<BranchModel>
    @Query("SELECT branches.id, branches.retailerId, branches.name AS branchName, retailers.name AS retailerName FROM branches INNER JOIN retailers ON branches.retailerId = retailers.id WHERE branches.retailerId = :retailerId")
    fun getBranchesForRetailer(retailerId: Int): List<BranchModel>

    @Query("SELECT * FROM branches WHERE branches.retailerId = :retailerId")
    fun getBranchesByRetailer(retailerId: Int): LiveData<List<BranchEntity>>

    @Query("SELECT * FROM branches WHERE retailerId = :retailerId")
    fun getBranchesByRetailerId(retailerId: Int): LiveData<List<BranchEntity>>
}
