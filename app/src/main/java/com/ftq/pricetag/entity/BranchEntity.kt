package com.ftq.pricetag.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "branches")
data class BranchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val retailerId: Int,
    val name: String
)
