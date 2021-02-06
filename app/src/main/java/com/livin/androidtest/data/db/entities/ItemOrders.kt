package com.livin.androidtest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_orders")
data class ItemOrders(
    @PrimaryKey
    val id: Int,
    val itemType: Int? = null,
    val sortOrder: Int? = null,
)