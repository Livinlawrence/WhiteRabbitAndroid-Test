package com.livin.androidtest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carousels")
data class Carousels(
    @PrimaryKey
    val id: Int,
    val categoryId: Int? = null,
    val storeId: Int? = null,
    val productId: Int? = null,
    val image: String? = null
)