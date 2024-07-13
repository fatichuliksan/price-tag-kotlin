package com.ftq.pricetag.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ftq.pricetag.entity.TransactionEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionEntity: TransactionEntity)

    @Update
    suspend fun update(transactionEntity: TransactionEntity)

    @Delete
    suspend fun delete(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): LiveData<List<TransactionEntity>>
}