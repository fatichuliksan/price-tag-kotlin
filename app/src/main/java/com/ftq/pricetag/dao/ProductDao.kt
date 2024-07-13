package com.ftq.pricetag.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ftq.pricetag.entity.ProductEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
     fun insert(product: ProductEntity)

    @Update
     fun update(product: ProductEntity)

    @Delete
     fun delete(product: ProductEntity)

    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProducts(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProductsList(): List<ProductEntity>
}