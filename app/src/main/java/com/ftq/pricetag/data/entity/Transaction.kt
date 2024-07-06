package com.ftq.pricetag.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val retailer_id: Int,
    val branch_id: Int,
    val product_id: Int,
    val price: Double,
    val discount: Double,
    val sell_price: Double
)