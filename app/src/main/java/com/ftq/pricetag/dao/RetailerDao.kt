package com.ftq.pricetag.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ftq.pricetag.entity.RetailerEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface RetailerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(retailer: RetailerEntity)

    @Update
    fun update(retailer: RetailerEntity)

    @Delete
    fun delete(retailer: RetailerEntity)

    @Query("SELECT * FROM retailers WHERE id = :id")
    fun getRetailerById(id: Int): RetailerEntity?

    @Query("SELECT * FROM retailers ORDER BY name ASC")
    fun getAllRetailers(): List<RetailerEntity>

    @Query("SELECT * FROM retailers ORDER BY name ASC")
    fun getAllRetailersLiveDataList(): LiveData<List<RetailerEntity>>

    @Query("SELECT * FROM retailers ORDER BY name ASC")
    fun getAllRetailers2(): List<RetailerEntity>

    @Query("SELECT * FROM retailers WHERE id = :retailerId")
    fun getRetailer(retailerId: Int): LiveData<RetailerEntity>
}