package com.ftq.pricetag.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val retailer_id: Int,
    val branch_id: Int,
    val product_id: Int,
    val price: Double,
    val discount: Double,
    val sell_price: Double
)