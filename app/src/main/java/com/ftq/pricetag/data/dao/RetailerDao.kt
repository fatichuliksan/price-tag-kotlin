package com.ftq.pppb.price_tag.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ftq.pricetag.data.entity.Retailer

@Dao
interface RetailerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(retailer: Retailer)

    @Update
    fun update(retailer: Retailer)

    @Delete
    fun delete(retailer: Retailer)

    @Query("SELECT * FROM retailers WHERE id = :id")
    fun getRetailerById(id: Long): Retailer?

    @Query("SELECT * FROM retailers ORDER BY name ASC")
    fun getAllRetailers(): LiveData<List<Retailer>>

    @Query("SELECT * FROM retailers WHERE id = :retailerId")
    fun getRetailer(retailerId: Long): LiveData<Retailer>
}