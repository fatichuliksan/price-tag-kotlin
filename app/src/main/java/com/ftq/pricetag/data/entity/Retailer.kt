package com.ftq.pricetag.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "retailers")
data class Retailer(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)
