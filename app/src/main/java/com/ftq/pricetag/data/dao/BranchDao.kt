package com.ftq.pppb.price_tag.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ftq.pricetag.data.entity.Branch

@Dao
interface BranchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(branch: Branch)

    @Update
    suspend fun update(branch: Branch)

    @Delete
    suspend fun delete(branch: Branch)

    @Query("SELECT * FROM branches WHERE retailer_id = :retailerId ORDER BY name ASC")
    fun getBranchesByRetailer(retailerId: Int): LiveData<List<Branch>>
}