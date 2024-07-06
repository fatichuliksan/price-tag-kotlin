package com.ftq.pricetag.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "branches")
data class Branch(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val retailer_id: Int,
    val name: String
)
