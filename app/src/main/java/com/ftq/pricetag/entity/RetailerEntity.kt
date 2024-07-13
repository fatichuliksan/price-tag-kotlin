package com.ftq.pricetag.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "retailers")
data class RetailerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
